package org.geworkbench.components.genomebrowser;

import com.affymetrix.genometry.AnnotatedBioSeq;
import com.affymetrix.genometry.span.SimpleSeqSpan;
import com.affymetrix.genometry.symmetry.SingletonSeqSymmetry;
import com.affymetrix.igb.IGB;
import com.affymetrix.igb.genometry.SimpleSymWithProps;
import com.affymetrix.igb.genometry.SmartAnnotBioSeq;
import com.affymetrix.igb.view.SeqMapView;
import distributions.BinomialDistribution;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Vector;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import net.sf.cglib.proxy.NoOp;
import org.geworkbench.bison.datastructure.biocollections.DSDataSet;
import org.geworkbench.bison.datastructure.biocollections.microarrays.CSChipchipSet;
import org.geworkbench.bison.datastructure.complex.pattern.matrix.CSPSAMMatch;
import org.geworkbench.bison.datastructure.complex.pattern.matrix.CSPSAMRegistration;
import org.geworkbench.bison.datastructure.complex.pattern.matrix.CSPositionSpecificAffinityMatrix;
import org.geworkbench.engine.config.VisualPlugin;
import org.geworkbench.engine.management.AcceptTypes;
import org.geworkbench.engine.management.Overflow;
import org.geworkbench.engine.management.Subscribe;
import org.geworkbench.events.ProjectEvent;

/**
 * Tool to display results from analysis of a ChIP on chip experiment in 
 * addition to be a comprehensive Genome Browser
 * Created on Aug 17, 2007, 1:56:26 PM
 * @throws <code>UnsupportedOperationException</code> some methods are not implemented as yet
 * @author manjunath at c2b2 dot columbia dot edu
 */

@AcceptTypes({DSDataSet.class})
public class GenomeBrowser implements VisualPlugin {
    
    IGB wrapper = null;
    JPanel displayPanel = null;
    Object igbViewerEnhancer = null;
    DSDataSet<CSPSAMMatch<CSPositionSpecificAffinityMatrix, CSPSAMRegistration>> dataset = null;
    
    /**
     * Default constructor
     */ 
    public GenomeBrowser() {
        
        // Best to call startup code using reflection
        Class igb = IGB.class;
        try {
            Method startup = igb.getMethod("main", new Class[]{String[].class});
            startup.invoke(null, new Object[]{new String[]{}});
        } catch (NoSuchMethodException nsme) {
        } catch (IllegalAccessException iae) {
        } catch (InvocationTargetException ite) {
        }
        
        wrapper = IGB.getSingletonIGB();
        
        SeqMapViewInterceptor igbViewerInterceptor = new SeqMapViewInterceptor(wrapper.getMapView().getClass());
        igbViewerEnhancer = igbViewerInterceptor.getEnhancer();
        
        wrapper.getFrame().setVisible(false);
        Container contentPane = wrapper.getFrame().getContentPane();
        JMenuBar menuBar = wrapper.getMenuBar();
        
        // Remove the "Exit" menu item
        menuBar.getMenu(0).remove(10);
        
        displayPanel = new JPanel();
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        SeqMapView mapView = wrapper.getMapView();
        JTabbedPane tabPane = wrapper.getTabPane();
        tabPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
        splitPane.setTopComponent(mapView);
        splitPane.setBottomComponent(tabPane);
        splitPane.setDividerLocation(0.5d);
        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(menuBar, BorderLayout.NORTH);
        displayPanel.add(splitPane, BorderLayout.CENTER);
        
        x = new int[80];
        y = new float[80];
        int i = 0;
        for (double d = -2.0; d <= 2.0; d+=0.05){
            x[i] = i;
            y[i++] = (float)bindist.gamma(d);
        }
    }
    
    /**
     * Abstract method implementation from <code>VisualPlugin</code>
     */
    public Component getComponent() {
        return displayPanel;
    }
    
    /**
     * Called when a data set is selected or cleared in the project panel.
     */
    @Subscribe(Overflow.class)
    public void receive(ProjectEvent projectEvent, Object source) {
        if (projectEvent.getMessage().equals(ProjectEvent.CLEARED)) {
            clearDataset();
        }
        dataset = projectEvent.getDataSet();
        if (dataset instanceof CSChipchipSet){
            boolean processed = processDataSet();
        }
    }
    
    void clearDataset(){
    }
    
    Vector<String> annotationIds = new Vector<String>();
    BinomialDistribution bindist = new BinomialDistribution();
    int[] x;float[] y;
    
    boolean processDataSet() {
        if (dataset != null) {
            AnnotatedBioSeq seq = wrapper.getMapView().getAnnotatedSeq();
            for (CSPSAMMatch<CSPositionSpecificAffinityMatrix, CSPSAMRegistration> match : dataset){
                CSPSAMRegistration reg = match.getRegistration();
                String chr = "chr" + reg.chromosome;
                if (seq.getID().equals(chr) && reg.pValue <= 0.05){
                    SimpleSymWithProps tfbs = new SimpleSymWithProps();
                    String id = reg.assembly + reg.chromosome + reg.x1 + reg.x2 + reg.pValue;
                    tfbs.setID(id);
                    if (!annotationIds.contains(id)){
                        tfbs.addSpan(new SimpleSeqSpan(reg.x1, reg.x2, seq));
                        tfbs.addChild(new SingletonSeqSymmetry(reg.x1, reg.x1, seq));
//                        tfbs.addChild(new GraphSym(x, y, id, seq));
                        tfbs.addChild(new SingletonSeqSymmetry(reg.x2, reg.x2, seq));
                        tfbs.setProperty("method", "chipchip");
                        synchronized (seq)  { ((SmartAnnotBioSeq)seq).addAnnotation(tfbs); }
                        annotationIds.add(id);
                    }
                }
            }
            return true;
        }
        return false;
    }
        
    /**
     * Inner class that handles the CGLIB extension for painting glyphs in IGB.
     */
    private class SeqMapViewInterceptor implements MethodInterceptor {
        
        Class base;
        
        public SeqMapViewInterceptor(Class base) {
            this.base = base;
        }
        
        /**
         * Intercepts method calls of the super-class and performs painting actions
         */
        public Object intercept(Object callingObject, Method method, Object[] params, MethodProxy methodProxy) throws Throwable {
            Class returnType = method.getReturnType();
            if (method.getName().equals("getAxisTier")){
                Object result = methodProxy.invokeSuper(callingObject, params);
                processDataSet();
                return result;
            }
            // Call original method
            Object result = methodProxy.invokeSuper(callingObject, params);
            return result;
        }
        
        public Object getEnhancer() {
            Enhancer enhancer = new Enhancer();
            enhancer.setClassLoader(base.getClassLoader());
            enhancer.setSuperclass(base);
            // Indicates that no callback should be used for this method
            Callback noOp = NoOp.INSTANCE;
            enhancer.setCallbacks(new Callback[]{noOp, this});
            CallbackFilter filter = new CallbackFilter() {
                public int accept(Method method) {
                    if (method.getName().startsWith("get") || method.getName().startsWith("set") || method.getName().startsWith("add")){
                        return 1;
                    }
                    return 0;
                }
            };
            enhancer.setCallbackFilter(filter);
            return base.cast(enhancer.create());
        }
    }
}