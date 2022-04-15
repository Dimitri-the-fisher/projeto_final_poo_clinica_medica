package interfacegrafica;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class MedicoController implements Initializable {

	@FXML
	private TextField txtCodMed;
	@FXML
	private TextField txtNomeMed;
	@FXML
	private TextField txtTelefoneMed;
	@FXML
	private TextField txtSenhaMed;
	@FXML
	TextField txtEmaiMed;
	@FXML
	private ComboBox<String> cboSetorMed;
	@FXML
	private Label lblMensagem;
	
//Botões
	@FXML
	private Button btnConfirmar;
	@FXML
	private Button btnLimpar;
	@FXML
	private Button btnIncluir;
	@FXML
	private Button btnExcluir;
	@FXML
	private Button btnAlterar;
	@FXML
	private Button btnConsultar;
	//variáveis para receber os dados
	private int MEDCRM;
	private String MEDNOME;
	private String bairroFunc;
	private String MEDESP;
	private String MEDTEL;
	private String MEDEMAIL;
	private String MEDSENHA;
	//variável para definir a operação selecionada: 
                                     //incluir/excluir/
	private String operacao;
	
// Lista as especialidades
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		cboSetorMed.getItems().addAll("OUVIDO", "NARIZ","GARGANTA");
		
	}
	
	// CRUD Inserir, Alterar, Remover e Consultar.
	public void onBtnIncluir()
	{

		incluir();
	}
	public void onBtnExcluir()
	{
		excluir();
	}
	public void onBtnAlterar()
	{
	
		alterar();
	}
	public void onBtnConsultar()
	{
		consultar();
	}
	public void onBtnLimpar() {
		txtCodMed.setText("");
		txtNomeMed.setText("");
		txtTelefoneMed.setText("");
		txtSenhaMed.setText("");
		txtEmaiMed.setText("");
		cboSetorMed.setDisable(true);
		lblMensagem.setText("Dados limpos!");
	}
	
	public void onKeyRelesead() {

		boolean calcular;
		boolean limpar;
		
		calcular=(txtNomeMed.getText().isEmpty() | txtCodMed.getText().isEmpty());
		btnIncluir.setDisable(calcular);
		btnConfirmar.setDisable(calcular);
		btnIncluir.setDisable(calcular);
		btnExcluir.setDisable(calcular);
		btnAlterar.setDisable(calcular);
		
		limpar=(txtNomeMed.getText().isEmpty() & txtCodMed.getText().isEmpty() & txtNomeMed.getText().isEmpty());
		btnLimpar.setDisable(limpar);

		
		
		cboSetorMed.setDisable(false);
			
	}
	public void onKeyConfirmar() {
		 
		lblMensagem.setText("Dados Inseridos Com sucesso ");
	}
	
	public void incluir() {
		
		Connection banco=abreBanco();
		PreparedStatement ps;
		ResultSet rs=null;
		String sql;
		String teste;
		MEDNOME=txtNomeMed.getText();
		MEDTEL=txtTelefoneMed.getText();
		
		MEDEMAIL=txtEmaiMed.getText();
		MEDESP=cboSetorMed.getValue();
		MEDSENHA=txtSenhaMed.getText();
			
	try 
	{
			 
		sql="Insert into MEDICO (MEDNOME, MEDTEL, MEDEMAIL, MEDESP,MEDSENHA)"
                + " values (?,?,?,?,?)";
		ps=banco.prepareStatement(sql);
		ps.setString(1, MEDNOME);
		ps.setString(2, MEDTEL);
		ps.setString(3, MEDEMAIL);
		ps.setString(4, MEDESP);
		ps.setString(5, MEDSENHA);
		
		int rsAltera = ps.executeUpdate();  
		sql="Select max(MEDCRM) as maior from MEDICO";
		ps=banco.prepareStatement(sql);
		rs = ps.executeQuery();
		while (rs.next()) {
			//imprimir os dados para ver se funcionou, depois eu jogo na tela
			txtCodMed.setText(rs.getString("maior"));
			}
		lblMensagem.setText(MEDNOME + " incluído(a) no banco de dados");
		rs.close();
		banco.close();
		}
	catch (SQLException e) {

		throw new RuntimeException(e);
		} //fim do try
	
} // Fim do incluir

public void excluir() {
	Connection banco=abreBanco();
	PreparedStatement ps;
	ResultSet rs=null;
	String sql;
	//armazenar os dados do formulário nas variáveis
	MEDCRM=Integer.parseInt(txtCodMed.getText());
	MEDNOME=txtNomeMed.getText();
	try 
	{
		sql="Delete from MEDICO where MEDCRM= " + MEDCRM;
		ps=banco.prepareStatement(sql);
		int rsAltera = ps.executeUpdate();
		lblMensagem.setText(MEDNOME + " excluído(a) no banco de dados");
	}
	catch (SQLException e) {
		throw new RuntimeException(e);
	}
	
}	
	
	

// Método Consultar

public void consultar()
{
	System.out.println ("Chamou o método consultar");
	Connection banco=abreBanco();
	PreparedStatement ps;
	ResultSet rs=null;
	String sql;
	boolean achou=false;

	//armazenar os dados do formulário nas variáveis
	MEDCRM=Integer.parseInt(txtCodMed.getText());
	try {
		sql="Select * from MEDICO where MEDCRM= " + MEDCRM;
		
		ps=banco.prepareStatement(sql);
		System.out.println (sql);
		rs=ps.executeQuery();
	while (rs.next()) {	
		txtNomeMed.setText(rs.getString("MEDNOME"));
		txtTelefoneMed.setText(rs.getString("MEDTEL"));
		cboSetorMed.setValue(rs.getString("MEDESP"));
		txtSenhaMed.setText(rs.getString("MEDSENHA"));
		txtEmaiMed.setText(rs.getString("MEDEMAIL"));
		achou=true;
		lblMensagem.setText("");
		lblMensagem.setText("Código Encontrado com sucesso !");
	}

}
	catch (SQLException e) {
	
		throw new RuntimeException(e);
	}

if (!achou)
	lblMensagem.setText("Código não encontrado no banco de dados");
}

// Fim do método Consultar


// Método Alterar

public void alterar() {
	Connection banco=abreBanco();
	PreparedStatement ps;
	ResultSet rs=null;
	String sql;

	//armazenar os dados do formulário nas variáveis
	MEDCRM=Integer.parseInt(txtCodMed.getText());

	MEDNOME=txtNomeMed.getText();
	MEDTEL=txtTelefoneMed.getText();
	
	MEDEMAIL=txtEmaiMed.getText();
	MEDESP=cboSetorMed.getValue();
	MEDSENHA=txtSenhaMed.getText();
	
	
	try {
		sql="Update medico " +
		"set MEDNOME=?, MEDTEL=?, MEDESP=?,MEDSENHA=?,MEDEMAIL=? " + " where MEDCRM=?";            

		ps=banco.prepareStatement(sql);
		ps.setString(1, MEDNOME); 
		ps.setString(2, MEDTEL);
		ps.setString(3, MEDESP);
		ps.setString(4, MEDSENHA);
		ps.setString(5, MEDEMAIL);
		ps.setInt(6, MEDCRM);
		ps.executeUpdate();

		ps.close();
		banco.close();
		lblMensagem.setText(MEDNOME + " alterado (a)");
		}
	  catch (SQLException e) {
	    throw new RuntimeException(e);
		}
	}

//Fim do método Alterar



	// Abre conexão com o Banco de dados.
	
	public static Connection abreBanco() {
		
		final String BANCO = "jdbc:mysql://localhost:3306/recife";
		
		try {
			return DriverManager.getConnection(BANCO, "root", "");
		}
		
		catch(SQLException e) {
			
			throw new RuntimeException(e);		
		}
	}
}
