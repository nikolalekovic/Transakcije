import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import beans.Isplata;
import beans.Transakcija;
import beans.Uplata;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldSuma;
	private JTextField textFieldDan;
	private JTextField textFieldMesec;
	private JTextField textFieldGodina;
	private JTextField textField_4;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public GUI() throws IOException {
		
		Transakcija t = new Transakcija();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 582);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Uplata/Isplata", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(17, 9, 483, 164);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JRadioButton RadioUplata = new JRadioButton("Uplata");
		RadioUplata.setSelected(true);
		buttonGroup.add(RadioUplata);
		RadioUplata.setBounds(135, 16, 109, 23);
		panel.add(RadioUplata);
		
		JRadioButton RadioIsplata = new JRadioButton("Isplata");
		buttonGroup.add(RadioIsplata);
		RadioIsplata.setBounds(260, 16, 109, 23);
		panel.add(RadioIsplata);
		
		textFieldSuma = new JTextField();
		textFieldSuma.setBounds(190, 66, 144, 20);
		panel.add(textFieldSuma);
		textFieldSuma.setColumns(10);
		
		textFieldDan = new JTextField();
		textFieldDan.setBounds(64, 119, 61, 20);
		panel.add(textFieldDan);
		textFieldDan.setColumns(10);
		
		textFieldMesec = new JTextField();
		textFieldMesec.setBounds(190, 119, 99, 20);
		panel.add(textFieldMesec);
		textFieldMesec.setColumns(10);
		
		textFieldGodina = new JTextField();
		textFieldGodina.setBounds(355, 119, 109, 20);
		panel.add(textFieldGodina);
		textFieldGodina.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Suma:");
		lblNewLabel.setBounds(134, 69, 46, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Dan:");
		lblNewLabel_1.setBounds(22, 122, 46, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Mesec:");
		lblNewLabel_2.setBounds(135, 122, 46, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Godina:");
		lblNewLabel_3.setBounds(299, 122, 46, 14);
		panel.add(lblNewLabel_3);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setBounds(202, 184, 187, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		textField_4.setText(""+t.Balans());
		
		ArrayList<Uplata> ul = new ArrayList<Uplata>();
		ArrayList<Isplata> il = new ArrayList<Isplata>();
		
		
		JLabel lblStanjeNaRacunu = new JLabel("Stanje na racunu:");
		lblStanjeNaRacunu.setBounds(93, 187, 131, 14);
		contentPane.add(lblStanjeNaRacunu);
		JLabel label = new JLabel("New label");
		label.setText("");
		label.setBounds(163, 260, 187, 14);
		contentPane.add(label);
		label.setHorizontalAlignment(JLabel.CENTER);
		JButton btnIzvrsiTansakciju = new JButton("Izvrsi tansakciju");
		btnIzvrsiTansakciju.addActionListener (new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				if (RadioUplata.isSelected())
				{
					try {
						Transakcija t = new Transakcija();
						Uplata u = new Uplata(textFieldDan.getText(),textFieldMesec.getText(),textFieldGodina.getText(),Integer.parseInt(textFieldSuma.getText()));
						t.Ispis(u);
						t.Log(u);
						label.setForeground(Color.black);
						label.setText("Uplata je uspesna");
						ul.add(u);
						
					} catch (Exception e) {
						label.setForeground(Color.red);
						label.setText("Uplata nije uspesna");
						
						textFieldDan.setText("");
						textFieldMesec.setText("");
						textFieldGodina.setText("");
						textFieldSuma.setText("");
					}
					
				}
				else
				{
					try {
						if(Integer.parseInt(textFieldSuma.getText())<=t.Balans())
						{
						Transakcija t = new Transakcija();
						Isplata i = new Isplata(textFieldDan.getText(),textFieldMesec.getText(),textFieldGodina.getText(),Integer.parseInt(textFieldSuma.getText()));
						t.Ispis(i);
						t.Log(i);
						label.setForeground(Color.black);
						label.setText("Isplata je uspesna");
						}
						else
						{
							throw new Exception("");
						} 
					} catch (Exception e) {
						// TODO Auto-generated catch block
						label.setForeground(Color.red);
						label.setText("Isplata nije uspesna");
						
						textFieldDan.setText("");
						textFieldMesec.setText("");
						textFieldGodina.setText("");
						textFieldSuma.setText("");
					}
				}
				try {
					textField_4.setText(""+t.Balans());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnIzvrsiTansakciju.setBounds(163, 226, 187, 23);
		contentPane.add(btnIzvrsiTansakciju);
		
		JButton btnNewButton = new JButton("Prikazi transakcije");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (int i=0;i<ul.size();i++)
				{
					
					System.out.println(ul.get(i).getSuma()+" "+ul.get(i).getDate());
				}
			}
		});
		btnNewButton.setBounds(163, 285, 187, 23);
		contentPane.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "JPanel title", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(11, 315, 241, 225);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 25, 225, 193);
		panel_1.add(scrollPane);
		
		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "JPanel title", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(263, 319, 251, 225);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 26, 231, 187);
		panel_2.add(scrollPane_1);
		
		JTextPane textPane_1 = new JTextPane();
		scrollPane_1.setViewportView(textPane_1);
		
		
	}
}
