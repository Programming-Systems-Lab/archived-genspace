package org.geworkbench.components.simulation.networkProcessor;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import org.geworkbench.util.Util;
import org.systemsbiology.chem.Model;
import org.systemsbiology.chem.Reaction;
import org.systemsbiology.math.Value;

public class ReactionEfficiencyProcessor implements INetworkProcessor{
    double reactionNoise;

    public ReactionEfficiencyProcessor() {
    }

    /**
     * initialize
     *
     * @param propsMap HashMap
     */
    public void initialize(HashMap propsMap) {
        this.reactionNoise = Util.getDouble(propsMap, "ReactionNoise");
    }

    /**
     * processNetwork
     *
     * @return Model
     */
    public void processNetwork(Model model) {

        Collection reactions = model.getReactions();
        Iterator reactionIt = reactions.iterator();

        while(reactionIt.hasNext()){
            Reaction reaction = (Reaction)reactionIt.next();
            Value value = reaction.getValue();
            PerturbableValue newValue = new PerturbableValue(value);

            double noise = reactionNoise - (Math.random() * reactionNoise * 2) + 1;
//            double noise = 1;
            newValue.setPerturbation(noise);
            reaction.setValue(newValue);
        }
    }
}
