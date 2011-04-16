package org.geworkbench.components.genspace.rating;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
<<<<<<< HEAD
=======
import java.util.concurrent.ExecutionException;
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

<<<<<<< HEAD
import org.geworkbench.components.genspace.LoginManager;
import org.geworkbench.components.genspace.ServerConfig;
import org.geworkbench.components.genspace.bean.RatingBean;
=======
import org.geworkbench.components.genspace.GenSpace;
import org.geworkbench.components.genspace.LoginFactory;
import org.geworkbench.components.genspace.entity.Tool;
import org.geworkbench.components.genspace.entity.ToolRating;
import org.geworkbench.components.genspace.entity.Workflow;
import org.geworkbench.components.genspace.entity.WorkflowRating;

>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
import javax.swing.SwingWorker;

public class StarRatingPanel extends JPanel implements MouseListener {

<<<<<<< HEAD
=======
	/**
	 * 
	 */
	private static final long serialVersionUID = 7212044466813119614L;
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
	public static final int SMALL = 1;
	public static final int MEDIUM = 2;
	public static final int LARGE = 3;

	private boolean clickable = true;
	private Star[] stars;
	private double value = 0;
<<<<<<< HEAD
	private int size = SMALL;
	private int id;
	private JLabel title;
	private JLabel ratingInfo;
	private Rater rater;
=======
//	private int size = SMALL;
	private Workflow workflow;
	private Tool tool;
	private JLabel title;
	private JLabel ratingInfo;
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7

	private Font titleFont = new Font("Verdana", Font.BOLD, 9);
	private Font ratingFont = new Font("Verdana", Font.PLAIN, 9);
	private JPanel starPanel = new JPanel();
	private JPanel contentPanel = new JPanel();
<<<<<<< HEAD
	private ServerConfig server;
	private String getCommand;
	private String writeCommand;
	private JComponent parent;

	public StarRatingPanel(JComponent parent, ServerConfig server,
			String getCommand, String writeCommand) {
		this(parent, "", -1, server, getCommand, writeCommand);
	}

	public StarRatingPanel(JComponent parent, String titleText, int id,
			ServerConfig server, String getCommand, String writeCommand) {

		rater = new Rater(server, writeCommand, getCommand);
=======

//	private JComponent parent;

	public StarRatingPanel() {
		this("", null);
	}

	public StarRatingPanel(String titleText, Tool tool) {

>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7

		contentPanel
				.setBorder(new MatteBorder(2, 2, 2, 2, this.getBackground()));
		add(contentPanel);

		// basic setup
		contentPanel.setLayout(new BorderLayout());
<<<<<<< HEAD
		this.id = id;
=======
		this.tool = tool;
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7

		// add title
		title = new JLabel(titleText);
		title.setFont(titleFont);
		contentPanel.add(title, BorderLayout.NORTH);

		// add rating info
		ratingInfo = new JLabel("");
		ratingInfo.setFont(ratingFont);
		contentPanel.add(ratingInfo, BorderLayout.EAST);

		// add stars
<<<<<<< HEAD
		stars = new Star[5];
		for (int i = 0; i < 5; i++)
			stars[i] = new Star(this, i + 1);
		contentPanel.add(starPanel, BorderLayout.WEST);
		for (int i = 0; i < 5; i++)
			starPanel.add(stars[i]);
=======
//		stars = new Star[5];
//		for (int i = 0; i < 5; i++)
//			stars[i] = new Star(this, i + 1);
//		contentPanel.add(starPanel, BorderLayout.WEST);
//		for (int i = 0; i < 5; i++)
//			starPanel.add(stars[i]);
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
	}

	public void setTitle(String t) {
		this.title.setText(t);
	}
<<<<<<< HEAD

	public void loadRating(final int id) {

		this.id = id;
		final String username = LoginManager.getUsername();

		// see if we can even execute the query
		if (id == -1) {
=======
	public void loadRating(Workflow wf) {
		this.workflow = wf;
		// see if we can even execute the query
		if (workflow == null || workflow.getId() < 1) {
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
			setVisible(false);
			return;
		} else
			setVisible(true);

<<<<<<< HEAD
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			@Override
			public Void doInBackground() {

				RatingBean rating = rater.getRating(id, username);

				if (rating != null) {
					setVisible(true);
					if (rating.getUserRating() != 0)
=======
		SwingWorker<WorkflowRating, Void> worker = new SwingWorker<WorkflowRating, Void>() {
			@Override
			public WorkflowRating doInBackground() {

				return LoginFactory.getPrivUsageFacade().getMyRating(workflow);
			}
			@Override
			protected void done() {
				WorkflowRating rating = null;
				try {
					rating = get();
				} catch (InterruptedException e) {
					GenSpace.logger.error("Unable to talk to server",e);
				} catch (ExecutionException e) {
					GenSpace.logger.error("Unable to talk to server",e);
				}
				if (rating != null) {
					setVisible(true);
					if (rating.getRating() != 0)
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
						setClickable(false);
					else
						setClickable(true);

<<<<<<< HEAD
					setRatingValue(rating.getOverallRating(),
							rating.getTotalRatings());
				} else
					setVisible(false);

				return null;
=======
					setRatingValue(tool.getOverallRating(),
							tool.getNumRating());
				} else
					setVisible(false);
				super.done();
			}
		};
		worker.execute();
	}
	public void loadRating(final Tool tn) {

		this.tool = tn;
		// see if we can even execute the query
//		if (tn == null || tn.getId() < 1) {
//			System.out.println("Setting not visible");
//			setVisible(false);
//			return;
//		} else
//			setVisible(true);

		SwingWorker<ToolRating, Void> worker = new SwingWorker<ToolRating, Void>() {
			@Override
			public ToolRating doInBackground() {

				return LoginFactory.getPrivUsageFacade().getMyRating(tool);
			}
			@Override
			protected void done() {
				ToolRating rating = null;
				try {
					rating = get();
				} catch (InterruptedException e) {
					GenSpace.logger.error("Unable to talk to server",e);
				} catch (ExecutionException e) {
					GenSpace.logger.error("Unable to talk to server",e);
				}
				if (rating != null) {
					setVisible(true);
					if (rating.getRating() != 0)
						setClickable(false);
					else
						setClickable(true);

					setRatingValue(tool.getOverallRating(),
							tool.getNumRating());
				} else
					setVisible(false);
				super.done();
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
			}
		};
		worker.execute();
	}

	public void setRatingValue(double rating, long totalRatings) {
		if (totalRatings != 0) {
			setStarValue(rating);
			DecimalFormat twoDigit = new DecimalFormat("#,##0.00");

			ratingInfo.setText("(" + twoDigit.format(rating) + " by "
					+ totalRatings + " users.)");
		} else {
			setStarValue(0);
			ratingInfo.setText("Not yet rated.");
		}
	}

	public void setStarValue(double value) {
		this.value = value;

		for (int i = 1; i <= 5; i++) {
			if (value >= i)
				stars[i - 1].setStar(Star.FULL);
			else if (value > i - 1)
				stars[i - 1].setStar(Star.HALF);
			else
				stars[i - 1].setStar(Star.EMPTY);
		}
	}

	public JPanel getThisPanel() {
		return this;
	}
<<<<<<< HEAD

	public void rate(final int rating) {

		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			@Override
			public Void doInBackground() {

				String username = LoginManager.getUsername();

				// perform rating here
				RatingBean newRating = rater.writeRating(id, username, rating);

				if (newRating == null) {
=======
	public void rateWorkflow(final int rating) {

		SwingWorker<Workflow, Void> worker = new SwingWorker<Workflow, Void>() {
			@Override
			public Workflow doInBackground() {
				WorkflowRating tr = new WorkflowRating();
				tr.setWorkflow(workflow);
				tr.setRating(rating);
				return LoginFactory.getPrivUsageFacade().saveRating(tr);
			}
			@Override
			protected void done() {
				Workflow result = null;
				try {
					result = get();
				} catch (InterruptedException e) {
					GenSpace.logger.error("Unable to talk to server",e);
				} catch (ExecutionException e) {
					GenSpace.logger.error("Unable to talk to server",e);
				}
				if (result == null) {
					JOptionPane
							.showMessageDialog(null,
									"There was a problem in sending your rating.  Check your internet connection.");
				} else {
					setStarValue(result.getOverallRating());
					setRatingValue(result.getOverallRating(),
							result.getNumRating());
					// user can no longer rate now
					clickable = false;
					setTitle("Thanks!");
					getThisPanel().repaint();
				}
				super.done();
			}
		};
		worker.execute();
	}
	public void rateTool(final int rating) {

		SwingWorker<Tool, Void> worker = new SwingWorker<Tool, Void>() {
			@Override
			public Tool doInBackground() {
				ToolRating tr = new ToolRating();
				tr.setTool(tool);
				tr.setRating(rating);
				return LoginFactory.getPrivUsageFacade().saveRating(tr);
			}
			@Override
			protected void done() {
				Tool result = null;
				try {
					result = get();
				} catch (InterruptedException e) {
					GenSpace.logger.error("Unable to talk to server",e);
				} catch (ExecutionException e) {
					GenSpace.logger.error("Unable to talk to server",e);
				}
				if (result == null) {
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
					JOptionPane
							.showMessageDialog(null,
									"There was a problem in sending your rating.  Check your internet connection.");
				} else {
<<<<<<< HEAD
					setStarValue(newRating.getOverallRating());
					setRatingValue(newRating.getOverallRating(),
							newRating.getTotalRatings());
=======
					setStarValue(result.getOverallRating());
					setRatingValue(result.getOverallRating(),
							result.getNumRating());
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
					// user can no longer rate now
					clickable = false;
					setTitle("Thanks!");
					getThisPanel().repaint();
				}
<<<<<<< HEAD
				return null;
=======
				super.done();
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
			}
		};
		worker.execute();
	}

	public boolean isClickable() {
		return clickable;
	}

	public void setClickable(boolean c) {
		clickable = c;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (clickable)
<<<<<<< HEAD
			rate(((Star) e.getComponent()).getValue());
=======
			if(workflow != null)
				rateWorkflow(((Star) e.getComponent()).getValue());
			else
				rateTool(((Star) e.getComponent()).getValue());
>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (!clickable)
			return;

		int starIndex = ((Star) e.getComponent()).getValue() - 1;
		for (int i = 0; i < 5; i++) {
			if (i <= starIndex)
				stars[i].setStar(Star.FULL);
			else
				stars[i].setStar(Star.EMPTY);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (!clickable)
			return;
		setStarValue(value);
	}

	// these aren't needed.
	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

<<<<<<< HEAD
=======
	

>>>>>>> 1503fb7409898175766dea9b5bf0f562768a49b7
}
