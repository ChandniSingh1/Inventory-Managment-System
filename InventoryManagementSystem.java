import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;

public class InventoryManagementSystem {

	private JFrame frame;
	private JTextField txtiname;
	private JTextField txttype;
	private JTextField txtpprice;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTextField txtid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InventoryManagementSystem window = new InventoryManagementSystem();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InventoryManagementSystem() {
		initialize();
		Connect();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	public void Connect()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/inventory", "root", ""); 
		}
		catch (ClassNotFoundException ex)
		{
			ex.printStackTrace();
		
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		
	    }
	}
	
	public void table_load()
	{
	    try 
	    {
	    pst = con.prepareStatement("select * from inventory");
	    rs = pst.executeQuery();
	    table_2.setModel(DbUtils.resultSetToTableModel(rs));
	} 
	    catch (SQLException e) 
	     {
	        e.printStackTrace();
	  } 
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1196, 663);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Inventory Management System");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 60));
		lblNewLabel.setBounds(107, 11, 962, 120);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panel.setBounds(55, 150, 631, 273);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Item Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_1.setBounds(10, 33, 229, 61);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Price");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_1_1.setBounds(10, 175, 229, 61);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Type");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_1_2.setBounds(10, 105, 229, 61);
		panel.add(lblNewLabel_1_2);
		
		txtiname = new JTextField();
		txtiname.setBounds(231, 61, 271, 33);
		panel.add(txtiname);
		txtiname.setColumns(10);
		
		txttype = new JTextField();
		txttype.setColumns(10);
		txttype.setBounds(231, 133, 271, 31);
		panel.add(txttype);
		
		txtpprice = new JTextField();
		txtpprice.setColumns(10);
		txtpprice.setBounds(231, 203, 271, 33);
		panel.add(txtpprice);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				
			String iname,type,price;
			iname = txtiname.getText();
			type = txttype.getText();
			price = txtpprice.getText();
		   
		                
		     try {
		        pst = con.prepareStatement("insert into inventory(name,edition,price)values(?,?,?)");
		        pst.setString(1, iname);
		        pst.setString(2, type);
		        pst.setString(3, price);
		        pst.executeUpdate();
		        JOptionPane.showMessageDialog(null, "Item Added Sucessfully");
		        table_load();
		                       
		        txtiname.setText("");
				txttype.setText("");
				txtpprice.setText("");
				txtiname.requestFocus();
		       }
		    catch (SQLException e1) 
		        {            
		       e1.printStackTrace();
		    }
		}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setForeground(new Color(0, 0, 205));
		btnNewButton.setBounds(55, 434, 161, 83);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setForeground(new Color(0, 0, 205));
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnExit.setBounds(257, 434, 161, 83);
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtiname.setText("");
				txttype.setText("");
				txtpprice.setText("");
				txtiname.requestFocus();
				
				
			}
		});
		btnClear.setForeground(new Color(0, 0, 255));
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnClear.setBounds(467, 434, 161, 83);
		frame.getContentPane().add(btnClear);
		
		table = new JTable();
		table.setBounds(732, 522, 405, -353);
		frame.getContentPane().add(table);
		
		table_1 = new JTable();
		table_1.setBounds(902, 383, 1, 1);
		frame.getContentPane().add(table_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(700, 150, 454, 338);
		frame.getContentPane().add(scrollPane);
		
		table_2 = new JTable();
		scrollPane.setViewportView(table_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(55, 528, 587, 83);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		txtid = new JTextField();
		txtid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
                try {
                    
                    String id = txtid.getText();
                        pst = con.prepareStatement("select name,edition,price from inventory where id = ?");
                        pst.setString(1, id);
                        ResultSet rs = pst.executeQuery();
                    if(rs.next()==true)
                    {
                      
                        String name = rs.getString(1);
                        String edition = rs.getString(2);
                        String price = rs.getString(3);
                        
                        txtiname.setText(name);
                        txttype.setText(edition);
                        txtpprice.setText(price);

                    }   
                    else
                    {
                        txtiname.setText("");
                        txttype.setText("");
                        txtpprice.setText("");
                         
                    }
                } 
            
             catch (SQLException ex) {
                   
                }
        }
			
		});
		txtid.setColumns(10);
		txtid.setBounds(170, 25, 320, 31);
		panel_1.add(txtid);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Item ID");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_1_2_1.setBounds(10, 25, 164, 31);
		panel_1.add(lblNewLabel_1_2_1);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
String name,edition,price,bid;
                
                
                name = txtiname.getText();
                edition = txttype.getText();
                price = txtpprice.getText();
                bid  = txtid.getText();
                
                 try {
                        pst = con.prepareStatement("update inventory set name= ?,edition=?,price=? where id =?");
                        pst.setString(1, name);
                        pst.setString(2, edition);
                        pst.setString(3, price);
                        pst.setString(4, bid);
                        pst.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Record has been updated sucessfully.");
                        table_load();
                       
                        txtiname.setText("");
                        txttype.setText("");
                        txtpprice.setText("");
                        txtiname.requestFocus();
                    }
                    catch (SQLException e1) {
                        
                        e1.printStackTrace();
                    }
			}
		});
		btnUpdate.setForeground(new Color(0, 0, 255));
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnUpdate.setBounds(724, 522, 161, 83);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bid;
		           bid  = txtid.getText();
		           
		            try {
		                   pst = con.prepareStatement("delete from inventory where id =?");
		           
		                   pst.setString(1, bid);
		                   pst.executeUpdate();
		                   JOptionPane.showMessageDialog(null, "Record deleted sucessfully.");
		                   table_load();
		                  
		                   txtiname.setText("");
		                   txttype.setText("");
		                   txtpprice.setText("");
		                   txtiname.requestFocus();
		               }
		               catch (SQLException e1) {
		                   
		                   e1.printStackTrace();
		               }
			}
		});
		btnDelete.setForeground(new Color(0, 0, 255));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnDelete.setBounds(976, 522, 161, 83);
		frame.getContentPane().add(btnDelete);
	}
}
