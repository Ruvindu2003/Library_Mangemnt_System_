package Forms;



import Controller.Book_Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Book;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import servicess.ServiceFactory;
import servicess.custom.Book_service;
import utill.ServiceType;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Add_Bokks_Form {
    public AnchorPane Ancor_Bookmangement;
    Book_Controller bookController=new Book_Controller();

    @FXML
    private TableColumn<?, ?> Colum_Lanvage;

    @FXML
    private TableColumn<?, ?> Colum_isbn;

    @FXML
    private TextField Txt_tiitle;

    @FXML
    private TableColumn<?, ?> colum_Author;

    @FXML
    private TableColumn<?, ?> colum_booksid;

    @FXML
    private TableColumn<?, ?> colum_tiittle;

    @FXML
    private TableView<Book> tabel_Books;

    @FXML
    private TextField txt_Author;

    @FXML
    private TextField txt_Bookid;

    @FXML
    private TextField txt_Isbn;

    @FXML
    private TextField txt_lanvage;

    Book_service bookInstance = ServiceFactory.getInstance().getServiceType(ServiceType.AddBooks);
    @FXML
    void btn_Add_Action(ActionEvent event) {
        bookInstance.Addbook(new Book(
                txt_Bookid.getText(),
                Txt_tiitle.getText(),
                txt_Author.getText(),
                txt_Isbn.getText(),
                txt_lanvage.getText()
        ));

    }

    @FXML
    void btn_Delete_Action(ActionEvent event) throws SQLException {
        Connection connection= DBConnection.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("Delete from Books  where BookID =?");

            preparedStatement.setString(1,txt_Bookid.getText());

            if (0 < preparedStatement.executeUpdate()){
                new Alert(Alert.AlertType.INFORMATION,"Sucsse Fully Delete").show();

            }else {
                new  Alert(Alert.AlertType.INFORMATION,"Not Sucsses Full Delete").show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btn_Search_Action(ActionEvent event) {

        Book book = bookInstance.SearcBooks(txt_Bookid.getText());

        if (book != null) {
            Txt_tiitle.setText(String.valueOf(book.getTiitle()));
            txt_Author.setText(String.valueOf(book.getAuthor()));
            txt_Isbn.setText(String.valueOf(book.getIsbn()));
            txt_lanvage.setText(String.valueOf(book.getLanvage()));

        }


    }

    @FXML
    void btn_View_Action(ActionEvent event) {
        colum_booksid.setCellValueFactory(new PropertyValueFactory<>("bookid"));
        colum_tiittle.setCellValueFactory(new PropertyValueFactory<>("tiitle"));
        colum_Author.setCellValueFactory(new PropertyValueFactory<>("Author"));
        Colum_isbn.setCellValueFactory(new PropertyValueFactory<>("Isbn"));
        Colum_Lanvage.setCellValueFactory(new PropertyValueFactory<>("lanvage"));
        Lodtable();

    }

    @FXML
    void btn_update_Action(ActionEvent event) {
             boolean b=bookInstance.UpdateBooks(new Book(
                     txt_Bookid.getText(),
                     Txt_tiitle.getText(),
                     txt_Author.getText(),
                     txt_Isbn.getText(),
                   txt_lanvage.getText()
       ));




    }
    public void Lodtable(){

        List<Book> books=bookInstance.getAll();
        System.out.println(books);
        ObservableList<Book> objects = FXCollections.observableArrayList();
        books.forEach(book -> objects.add(book));
        tabel_Books.setItems(objects);
    }

    public void btn_back_Action(ActionEvent actionEvent) {

        Stage stage=new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/DashBoard.fxml"))));
            stage.show();
            stage.setTitle("DashBord");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btn_Bck_Action(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage)Ancor_Bookmangement.getScene().getWindow();
        stage.close();
        stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/DashBoard.fxml   "))));

    }
}
