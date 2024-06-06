import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

public class PanelCReview extends JPanel {
    private JScrollPane sMain;
    private JPanel pContent;

    private JPanel pMain = new JPanel(new BorderLayout());
    private int ra = 0;
    Connection conn;
    Statement stat;
    public PanelCReview(Connection conn,int UserID) throws SQLException{
        this.conn = conn;
        stat = conn.createStatement();
    	cComponent(UserID);
        cLayOut();
    }

    private void cComponent(int UserID) {
        pContent = new JPanel();
        pContent.setLayout(new BoxLayout(pContent, BoxLayout.Y_AXIS));

        for (int i = 0;i < FrameCustomer.barList.size();i++) {
            JPanel pTemp = new JPanel(new BorderLayout());
            pTemp.setPreferredSize(new Dimension(700,100));

            JLabel lblName = new JLabel(FrameCustomer.barList.get(i).getName());
            JLabel lblDistrict = new JLabel(FrameCustomer.barList.get(i).getDistrict());
            JLabel lblReview = new JLabel();

            JTextField ta_Review = new JTextField(20);

            JButton bt_Review = new JButton("Add review");
            

            JRadioButton rb_1 = new JRadioButton("1");
            JRadioButton rb_2 = new JRadioButton("2");
            JRadioButton rb_3 = new JRadioButton("3");
            JRadioButton rb_4 = new JRadioButton("4");
            JRadioButton rb_5 = new JRadioButton("5");
            ButtonGroup rbGroup = new ButtonGroup();
            rbGroup.add(rb_1);rbGroup.add(rb_2);rbGroup.add(rb_3);rbGroup.add(rb_4);rbGroup.add(rb_5);

            JButton bt_Rate = new JButton("Rate");
            bt_Rate.addActionListener(new ActionListener() {
            
            	public void actionPerformed(ActionEvent event) {
                    int r = 0;
                    
                    if (rb_1.isSelected()) {
                        r = 1;
                        System.out.println(r + "is selected");
                    } else if (rb_2.isSelected()) {
                        r = 2;
                    } else if (rb_3.isSelected()) {
                        r = 3;
                    } else if (rb_4.isSelected()) {
                        r = 4;
                    } else if (rb_5.isSelected()) {
                        r = 5;
                    } else {
                        JOptionPane.showMessageDialog(null, "Please select your rate first!", "Select before Rating!", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    ra = r;
                    //TODO: update database
                    
                }
            });
            bt_Review.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent event) {
            	    String review = ta_Review.getText();
            	    lblReview.setText(review);

            	    // 声明 PreparedStatement 对象
            	    PreparedStatement pstmt = null;

            	    // 准备 SQL 查询语句
            	    String query = "INSERT INTO `Review`(`ReviewID`, `BarID`, `CustomerID`, `Rating`, `Comment`) VALUES (?, (SELECT Name FROM Bar WHERE Bar.Name = ?), ?, ?, ?)";

            	    try {
            	        // 创建 PreparedStatement 对象
            	        pstmt = conn.prepareStatement(query);

            	        // 循环添加批处理操作
            	        for (int i = 0; i < 10000; i++) {
            	            pstmt.setInt(1, i);
            	            pstmt.setString(2, lblName.getText());
            	            pstmt.setInt(3, UserID);
            	            pstmt.setInt(4, ra);
            	            pstmt.setString(5, review);
            	            pstmt.addBatch();
            	        }

            	        // 执行批处理操作
            	        pstmt.executeBatch();
            	    } catch (SQLException e) {
            	        // 处理异常
            	        e.printStackTrace();
            	    } finally {
            	        // 关闭 PreparedStatement 对象
            	        if (pstmt != null) {
            	            try {
            	                pstmt.close();
            	            } catch (SQLException e) {
            	                e.printStackTrace();
            	            }
            	        }
            	    }
            	}

            });

            JPanel pRight = new JPanel(new FlowLayout(FlowLayout.RIGHT,20,0));
            JPanel pLeft = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
            JPanel pCenter = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
            JPanel pBottom = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
            
            pRight.add(ta_Review);
            pRight.add(bt_Review);

            pLeft.add(lblName);
            pLeft.add(lblDistrict);
            
            pCenter.add(new JLabel("Your review: "));
            pCenter.add(lblReview);

            pBottom.add(new JLabel("Rating: "));
            pBottom.add(rb_1);
            pBottom.add(rb_2);
            pBottom.add(rb_3);
            pBottom.add(rb_4);
            pBottom.add(rb_5);
            pBottom.add(bt_Rate);

            pTemp.add(pLeft, BorderLayout.WEST);
            pTemp.add(pCenter, BorderLayout.CENTER);
            pTemp.add(pRight, BorderLayout.EAST);
            pTemp.add(pBottom, BorderLayout.SOUTH);

            pContent.add(pTemp);
        }

        sMain = new JScrollPane(pContent);
        pContent.setPreferredSize(new Dimension(700,FrameCustomer.barList.size()*75));
        sMain.setPreferredSize(new Dimension(800,500));
    }

    private void cLayOut() {
        pMain.add(sMain, BorderLayout.CENTER);

        add(pMain);
    }
}
