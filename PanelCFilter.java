import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import java.util.ArrayList; 

public class PanelCFilter extends JPanel {
    private JButton bt_style,bt_district,bt_open,bt_close;
    private JTextField tf_style = new JTextField(15),tf_district = new JTextField(15),tf_open = new JTextField(15),tf_close = new JTextField(15); 
    private JPanel pNorthbtGroup,pStyle,pDistrict,pOpen,pClose,pContent;
    private JScrollPane sMain;
    
    private ArrayList<BarObject> listSort = new ArrayList<>();

    private JPanel pMain = new JPanel(new BorderLayout(0, 0));
    Connection conn;
    Statement stat;
    private PanelCLikedList pc;
    public PanelCFilter(Connection conn,PanelCLikedList pc) throws SQLException{
        this.conn = conn;
        this.stat = conn.createStatement();
        this.pc = pc;
    	cButton();
        cComponent();
        cLayOut();
    }

    private void cComponent() {
        pStyle = new JPanel(new FlowLayout());
        pStyle.add(new JLabel("Sort by style: "));
        pStyle.add(tf_style);
        pStyle.add(bt_style);
        pStyle.setPreferredSize(new Dimension(200,50));
        
        pDistrict = new JPanel(new FlowLayout());
        pDistrict.add(new JLabel("Sort by district: "));
        pDistrict.add(tf_district);
        pDistrict.add(bt_district);
        pDistrict.setPreferredSize(new Dimension(200,50));

        pOpen = new JPanel(new FlowLayout());
        pOpen.add(new JLabel("Sort by opentime: "));
        pOpen.add(tf_open);
        pOpen.add(bt_open);
        pOpen.setPreferredSize(new Dimension(200,50));

        pClose = new JPanel(new FlowLayout());
        pClose.add(new JLabel("Sort by closetime: "));
        pClose.add(tf_close);
        pClose.add(bt_close);
        pClose.setPreferredSize(new Dimension(200,50));

        pNorthbtGroup = new JPanel(new GridLayout(2,2));
        pNorthbtGroup.add(pStyle);pNorthbtGroup.add(pDistrict);
        pNorthbtGroup.add(pOpen);pNorthbtGroup.add(pClose);
        pNorthbtGroup.setPreferredSize(new Dimension(800,100));

        pContent = new JPanel();
        pContent.setLayout(new BoxLayout(pContent, BoxLayout.Y_AXIS));
        
        sMain = new JScrollPane(pContent);
        pContent.setPreferredSize(new Dimension(700,listSort.size()*75));
        sMain.setPreferredSize(new Dimension(800,450));
    }

    private void cButton() {
        bt_style = new JButton("Sort");
        bt_style.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String style = tf_style.getText();
                
                
                buildBarList(0,style);
                //funtion
                
                
                cList();
            }
        });

        bt_district = new JButton("Sort");
        bt_district.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String district = tf_district.getText();
                buildBarList(1,district);
                //funtion
                cList();
            }
        });

        bt_open = new JButton("Sort");
        bt_open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String open = tf_open.getText();
                buildBarList(2,open);
                //funtion
                cList();
            }
        });

        bt_close = new JButton("Sort");
        bt_close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String close = tf_close.getText();
                buildBarList(3,close);
                //funtion
                cList();
            }
        });
    }

    private void cLayOut() {
        pMain.add(pNorthbtGroup,BorderLayout.NORTH);
        pMain.add(sMain,BorderLayout.CENTER);
        
        add(pMain);
    }

    private void buildBarList(int i,String sort) {
        listSort.clear();
        if(i == 0) {
        	String query1 = "SELECT Name,Style,District,OpenTime,CloseTime FROM d_bar WHERE d_bar.Style = '" + sort + "'";
        	ResultSet rs1;
    		try {
    			rs1 = stat.executeQuery(query1);
    				while(rs1.next()) {
    					String name = rs1.getString("Name");
            	        String sty = rs1.getString("Style");
            	        String dis = rs1.getString("District");
            	        String opt = rs1.getString("OpenTime");
            	        String clot = rs1.getString("CloseTime");
            	        BarObject barObject = new BarObject(name, sty, dis, opt, clot);
            	        listSort.add(barObject);
                }
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }else if(i == 1) {
        	String query1 = "SELECT Name,Style,District,OpenTime,CloseTime FROM d_bar WHERE d_bar.District = '" + sort + "'";
        	ResultSet rs1;
    		try {
    			rs1 = stat.executeQuery(query1);
    				while(rs1.next()) {
    					String name = rs1.getString("Name");
            	        String sty = rs1.getString("Style");
            	        String dis = rs1.getString("District");
            	        String opt = rs1.getString("OpenTime");
            	        String clot = rs1.getString("CloseTime");
            	        BarObject barObject = new BarObject(name, sty, dis, opt, clot);
            	        listSort.add(barObject);
                }
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }else if(i == 2) {
        	String query1 = "SELECT Name,Style,District,OpenTime,CloseTime FROM d_bar WHERE d_bar.OpenTime = '" + sort + "'";
        	ResultSet rs1;
    		try {
    			rs1 = stat.executeQuery(query1);
    				while(rs1.next()) {
    					String name = rs1.getString("Name");
            	        String sty = rs1.getString("Style");
            	        String dis = rs1.getString("District");
            	        String opt = rs1.getString("OpenTime");
            	        String clot = rs1.getString("CloseTime");
            	        BarObject barObject = new BarObject(name, sty, dis, opt, clot);
            	        listSort.add(barObject);
                }
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }else if(i == 3) {
        	String query1 = "SELECT Name,Style,District,OpenTime,CloseTime FROM d_bar WHERE d_bar.CloseTime = '" + sort + "'";
        	ResultSet rs1;
    		try {
    			rs1 = stat.executeQuery(query1);
    				while(rs1.next()) {
    					String name = rs1.getString("Name");
            	        String sty = rs1.getString("Style");
            	        String dis = rs1.getString("District");
            	        String opt = rs1.getString("OpenTime");
            	        String clot = rs1.getString("CloseTime");
            	        BarObject barObject = new BarObject(name, sty, dis, opt, clot);
            	        listSort.add(barObject);
                }
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }
        
       
        //listSort.add(FrameCustomer.barList.get(1));
        //listSort.add(FrameCustomer.barList.get(2));
        //listSort.add(FrameCustomer.barList.get(3));
    }

    private void cList() {
        pContent.removeAll();

        for (int i = 0;i < listSort.size();i++) {
            JPanel pTemp = new JPanel(new BorderLayout());
            pTemp.setPreferredSize(new Dimension(700,100));

            JLabel lblName = new JLabel(listSort.get(i).getName());
            JLabel lblDistrict = new JLabel(listSort.get(i).getDistrict());
            int j = i;
            JButton bt_AddFavor = new JButton("Add to favorite");
            bt_AddFavor.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    //funtion
                	BarObject barObject = listSort.get(j);
                    pc.addToLikedList(barObject);
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
            
            pRight.add(bt_AddFavor);
            pRight.add(bt_Check);

            pLeft.add(lblName);
            pLeft.add(lblDistrict);

            pTemp.add(pLeft, BorderLayout.WEST);
            pTemp.add(pRight, BorderLayout.EAST);

            pContent.add(pTemp);
        }

        pContent.revalidate();
        pContent.repaint();
    }

}
