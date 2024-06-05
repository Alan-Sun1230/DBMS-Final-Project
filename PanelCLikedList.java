import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class PanelCLikedList extends JPanel{
    private JPanel pContent;
    private JScrollPane sMain;

    private ArrayList<BarObject> listLiked = new ArrayList<>();

    private JPanel pMain = new JPanel(new BorderLayout());

    public PanelCLikedList() {
        buildBarList();
        cComponent();
        cLayOut();
    }

    private void cComponent() {
        pContent = new JPanel();
        pContent.setLayout(new BoxLayout(pContent, BoxLayout.Y_AXIS));

        updateContent(); // Initial population of the content

        sMain = new JScrollPane(pContent);
        pContent.setPreferredSize(new Dimension(700, listLiked.size() * 75));
        sMain.setPreferredSize(new Dimension(800, 500));
    }

    private void updateContent() {
        pContent.removeAll(); // Clear existing content

        for (int i = 0; i < listLiked.size(); i++) {
            JPanel pTemp = new JPanel(new BorderLayout());
            pTemp.setPreferredSize(new Dimension(400, 100));

            JLabel lblName = new JLabel(listLiked.get(i).getName());
            JLabel lblDistrict = new JLabel(listLiked.get(i).getDistrict());
            int j = i;
            JButton bt_Remove = new JButton("Remove from Liked List");
            bt_Remove.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    // Remove the item from the list
                    listLiked.remove(j);
                    // Update the content
                    updateContent();
                    pContent.revalidate();
                    pContent.repaint();
                }
            });

            JPanel pRight = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
            JPanel pLeft = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));

            pRight.add(bt_Remove);

            pLeft.add(lblName);
            pLeft.add(lblDistrict);

            pTemp.add(pLeft, BorderLayout.WEST);
            pTemp.add(pRight, BorderLayout.EAST);

            pContent.add(pTemp);
        }

        // Update the preferred size based on the new number of items
        pContent.setPreferredSize(new Dimension(700, listLiked.size() * 75));
    }

    private void cLayOut() {
        pMain.add(sMain);

        add(pMain);
    }
    

    
    private void buildBarList() {
        //listLiked.add(FrameCustomer.barList.get(1));
        //listLiked.add(FrameCustomer.barList.get(2));
        //listLiked.add(FrameCustomer.barList.get(3));
    }
    public void addToLikedList(BarObject barObject) {
        if (!listLiked.contains(barObject)) {
            listLiked.add(barObject);
            updateContent();
            pContent.revalidate();
            pContent.repaint();
        }
    }
}
