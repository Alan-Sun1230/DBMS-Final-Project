import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import java.util.ArrayList;

public class PanelCRecom extends JPanel{
    private JPanel pContent,pFirst,pSecond,pThird,pFirstSB,pSecondSB,pThirdSB;
    private JScrollPane sMain,sFirst,sSecond,sThird;
    private JLabel lbl1 = new JLabel(),lbl2 = new JLabel(),lbl3 = new JLabel();

    private ArrayList<BarObject> listFirst = new ArrayList<>(),listSecond = new ArrayList<>(),listThird = new ArrayList<>();

    private JPanel pMain = new JPanel(new BorderLayout());
    Connection conn;
    Statement stat;
    private PanelCLikedList pCLiked;
    
    public PanelCRecom(Connection conn,int userID,PanelCLikedList pCLiked) throws SQLException {
        this.conn = conn;
        stat = conn.createStatement();
        this.pCLiked = pCLiked;
    	buildBarList(conn,userID);
        cComponent();
        cLayOut();
    }

    private void cComponent() {
        pFirst = new JPanel();
        pFirstSB = new JPanel();
        pFirstSB.setLayout(new BoxLayout(pFirstSB, BoxLayout.Y_AXIS));
        for (int i = 0;i < listFirst.size();i++) {
            JPanel pTemp = new JPanel(new BorderLayout());
            pTemp.setPreferredSize(new Dimension(400,100));
            
            JLabel lblName = new JLabel(listFirst.get(i).getName());
            JLabel lblDistrict = new JLabel(listFirst.get(i).getDistrict());
            
            String time = "OPEN TIME: " + (listFirst.get(i).getOpenTime()) + " ~ " + (listFirst.get(i).getCloseTime());
            JLabel lblTime = new JLabel(time);
            int j = i;
            JButton bt_AddFavor = new JButton("Add to Liked List");
            bt_AddFavor.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    //funtion
                	BarObject barObject = listFirst.get(j);
                    pCLiked.addToLikedList(barObject);
                }
            });

            JButton bt_Check = new JButton("Check reviews");
            bt_Check.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    //funtion
                }
            });

            JPanel pRight = new JPanel(new FlowLayout(FlowLayout.RIGHT,20,0));
            JPanel pLeft = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
            JPanel pCenter = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
            
            pRight.add(bt_AddFavor);
            pRight.add(bt_Check);

            pLeft.add(lblName);
            pLeft.add(lblDistrict);
            
            pCenter.add(lblTime);

            pTemp.add(pLeft, BorderLayout.WEST);
            pTemp.add(pCenter, BorderLayout.CENTER);
            pTemp.add(pRight, BorderLayout.EAST);

            pFirstSB.add(pTemp);
        }
        
        sFirst = new JScrollPane(pFirstSB);
        pFirstSB.setPreferredSize(new Dimension(500,listFirst.size()*75));
        sFirst.setPreferredSize(new Dimension(600,200));
        pFirst.add(sFirst);


        pSecond = new JPanel();
        pSecondSB = new JPanel();
        pSecondSB.setLayout(new BoxLayout(pSecondSB, BoxLayout.Y_AXIS));
        for (int i = 0;i < listSecond.size();i++) {
            JPanel pTemp = new JPanel(new BorderLayout());
            pTemp.setPreferredSize(new Dimension(400,100));
            
            JLabel lblName = new JLabel(listSecond.get(i).getName());
            JLabel lblDistrict = new JLabel(listSecond.get(i).getDistrict());
            
            String time = "OPEN TIME: " + (listSecond.get(i).getOpenTime()) + " ~ " + (listSecond.get(i).getCloseTime());
            JLabel lblTime = new JLabel(time);
            int j = i;
            JButton bt_AddFavor = new JButton("Add to Liked List");
            bt_AddFavor.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    //funtion
                	BarObject barObject = listSecond.get(j);
                    pCLiked.addToLikedList(barObject);
                }
            });

            JButton bt_Check = new JButton("Check reviews");
            bt_Check.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    //funtion
                }
            });

            JPanel pRight = new JPanel(new FlowLayout(FlowLayout.RIGHT,20,0));
            JPanel pLeft = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
            JPanel pCenter = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
            
            pRight.add(bt_AddFavor);
            pRight.add(bt_Check);

            pLeft.add(lblName);
            pLeft.add(lblDistrict);
            
            pCenter.add(lblTime);

            pTemp.add(pLeft, BorderLayout.WEST);
            pTemp.add(pCenter, BorderLayout.CENTER);
            pTemp.add(pRight, BorderLayout.EAST);

            pSecondSB.add(pTemp);
        }
        
        sSecond = new JScrollPane(pSecondSB);
        pSecondSB.setPreferredSize(new Dimension(500,listSecond.size()*75));
        sSecond.setPreferredSize(new Dimension(600,200));
        pSecond.add(sSecond);


        pThird = new JPanel();
        pThirdSB = new JPanel();
        pThirdSB.setLayout(new BoxLayout(pThirdSB, BoxLayout.Y_AXIS));
        for (int i = 0;i < listThird.size();i++) {
            JPanel pTemp = new JPanel(new BorderLayout());
            
            JLabel lblName = new JLabel(listThird.get(i).getName());
            JLabel lblDistrict = new JLabel(listThird.get(i).getDistrict());
            
            String time = "OPEN TIME: " + (listThird.get(i).getOpenTime()) + " ~ " + (listThird.get(i).getCloseTime());
            JLabel lblTime = new JLabel(time);
            int j = i;
            JButton bt_AddFavor = new JButton("Add to Liked List");
            bt_AddFavor.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    //funtion
                	BarObject barObject = listThird.get(j);
                    pCLiked.addToLikedList(barObject);
                	
                }
            });

            JButton bt_Check = new JButton("Check reviews");
            bt_Check.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    //funtion
                }
            });

            JPanel pRight = new JPanel(new FlowLayout(FlowLayout.RIGHT,20,0));
            JPanel pLeft = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
            JPanel pCenter = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
            
            pRight.add(bt_AddFavor);
            pRight.add(bt_Check);

            pLeft.add(lblName);
            pLeft.add(lblDistrict);
            
            pCenter.add(lblTime);

            pTemp.add(pLeft, BorderLayout.WEST);
            pTemp.add(pCenter, BorderLayout.CENTER);
            pTemp.add(pRight, BorderLayout.EAST);

            pThirdSB.add(pTemp);
        }
        
        sThird = new JScrollPane(pThirdSB);
        pThirdSB.setPreferredSize(new Dimension(500,listThird.size()*75));
        sThird.setPreferredSize(new Dimension(600,200));
        pThird.add(sThird);

        pContent = new JPanel();
        pContent.setLayout(new BoxLayout(pContent, BoxLayout.Y_AXIS));
        pContent.add(lbl1);
        pContent.add(pFirst);
        pContent.add(lbl2);
        pContent.add(pSecond);
        pContent.add(lbl3);
        pContent.add(pThird);
        pContent.setPreferredSize(new Dimension(700,800));

        sMain = new JScrollPane(pContent);
        sMain.setPreferredSize(new Dimension(800,500));

    
        lbl1.setFont(new Font("Monospaced", Font.BOLD, 25));
        lbl1.setText("***");
        lbl2.setFont(new Font("Monospaced", Font.BOLD, 25));
        lbl2.setText("**");
        lbl3.setFont(new Font("Monospaced", Font.BOLD, 25));
        lbl3.setText("*");
    
        
    }

    private void cLayOut() {
        pMain.add(sMain);

        add(pMain);
    }

    private void buildBarList(Connection conn,int userID) {
        //TODO: build 3 list with FrameCustomer.barList in corresbonding method
        //following code is only for testing and avoiding exceptions
    	
    	
    	    String query1 = "SELECT DISTINCT db.UserID, db.Name " +
    	                    "FROM d_bar db " +
    	                    "JOIN d_customer dc ON db.Target_Audience = dc.Occupation " +
    	                    "AND db.Featured_Activity = dc.Preferred_Activity " +
    	                    "WHERE dc.UserID = " + userID;

    	    String query2 = "SELECT DISTINCT db.UserID, db.Name " +
                    "FROM d_bar db " +
                    "JOIN d_customer dc ON (db.Target_Audience = dc.Occupation " +
                    "OR db.Featured_Activity = dc.Preferred_Activity) " +
                    "WHERE dc.UserID = " + userID + " " +
                    "AND db.Name NOT IN (" +
                    "  SELECT db2.Name " +
                    "  FROM d_bar db2 " +
                    "  JOIN d_customer dc2 ON db2.Target_Audience = dc2.Occupation " +
                    "  AND db2.Featured_Activity = dc2.Preferred_Activity " +
                    "  WHERE dc2.UserID = " + userID + ")";

    	    try {
    	        Statement stat = conn.createStatement();

    	        // Query for listFirst
    	        ResultSet result1 = stat.executeQuery(query1);
    	        ArrayList<BarObject> bar1 = new ArrayList<>();
    	        while (result1.next()) {
    	            String barName = result1.getString("Name");
    	            for (BarObject bar : FrameCustomer.barList) {
    	                if (bar.getName().equals(barName)) {
    	                    bar1.add(bar);
    	                    break;
    	                }
    	            }
    	        }
    	        result1.close(); // Close result1 after processing

    	        if (bar1.isEmpty()) {
    	            BarObject noBar = new BarObject("null", "null", "null", "null", "null");
    	            listFirst.add(noBar);
    	        } else {
    	            listFirst.addAll(bar1);
    	        }

    	        // Query for listSecond
    	        ResultSet result2 = stat.executeQuery(query2);
    	        ArrayList<BarObject> bars2 = new ArrayList<>();
    	        while (result2.next()) {
    	            String barName = result2.getString("Name");
    	            for (BarObject bar : FrameCustomer.barList) {
    	                if (bar.getName().equals(barName)) {
    	                    bars2.add(bar);
    	                    break;
    	                }
    	            }
    	        }
    	        result2.close(); // Close result2 after processing

    	        if (bars2.isEmpty()) {
    	            BarObject noBar = new BarObject("null", "null", "null", "null", "null");
    	            listSecond.add(noBar);
    	        } else {
    	            listSecond.addAll(bars2);
    	        }

    	         // Close statement after all operations

    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }
    	}

    	
    	
        //listFirst.add(FrameCustomer.barList.get(0));
        //listFirst.add(FrameCustomer.barList.get(1));
        //listFirst.add(FrameCustomer.barList.get(3));
        //listFirst.add(FrameCustomer.barList.get(5));

        //listSecond.add(FrameCustomer.barList.get(2));
        //listSecond.add(FrameCustomer.barList.get(3));

        //listThird.add(FrameCustomer.barList.get(4));
        //listThird.add(FrameCustomer.barList.get(5));
    }

