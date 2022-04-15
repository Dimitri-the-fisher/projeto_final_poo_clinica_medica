package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent p = FXMLLoader.load(getClass().getResource("/interfacegrafica/Medico.fxml"));      // Para acessar M�dico 
		//Parent � uma superclasse do objeto AnchorPane
		//por isso � poss�vel carregar (Load) a view Empregado.fxml que � AnchorPane como Parent
			Scene cena = new Scene (p);
		//Scene � uma classe que seu construtor recebe um Parent como par�metro
			primaryStage.setScene(cena);
		//Observe que Stage recebe um atributo do tipo Scene, por isso o encapsulamento do atributo Scene 
			primaryStage.show();
		//O m�todo show da classe Stage (que � o palco) mostra o formul�rio
			String css = this.getClass().getResource("application.css").toExternalForm();
			cena.getStylesheets().add(css);
			primaryStage.setScene(cena);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		}
	
	public static void main(String[] args) {
		launch(args);
	}
}
