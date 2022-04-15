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
			Parent p = FXMLLoader.load(getClass().getResource("/interfacegrafica/Medico.fxml"));      // Para acessar Médico 
		//Parent é uma superclasse do objeto AnchorPane
		//por isso é possível carregar (Load) a view Empregado.fxml que é AnchorPane como Parent
			Scene cena = new Scene (p);
		//Scene é uma classe que seu construtor recebe um Parent como parâmetro
			primaryStage.setScene(cena);
		//Observe que Stage recebe um atributo do tipo Scene, por isso o encapsulamento do atributo Scene 
			primaryStage.show();
		//O método show da classe Stage (que é o palco) mostra o formulário
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
