import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ThirdProjController {
//	ArrayList<Vertex> vertices = new ArrayList<>();

	int numOfVertex = 0;
	int numOfEdges = 0;
	String[] num;
	String cityName = "";
	double lat = 0.0;
	double lng = 0.0;
	double minLat = 31.59489;
	double maxLat = 31.2201;
	double minLng = 34.21869;
	double maxLng = 34.56759;
	double wMinX = 0.0;
	double wMaxX = 800.0;
	double wMinY = 0.0;
	double wMaxY = 760.0;
	static String choser = "";
	HashMap<String, Vertex> hash = new HashMap<>();
	ArrayList<Vertex> pp = new ArrayList<>();
	static Vertex chosen;
	@FXML
	private HBox main;
	@FXML
	private AnchorPane imgAnchor;
	@FXML
	private ImageView gazaImg;
	@FXML
	private AnchorPane infoAnchor;
	@FXML
	private Label from;
	@FXML
	private ComboBox<String> srcCombo;
	@FXML
	private Label to;
	@FXML
	private ComboBox<String> destCombo;
	@FXML
	private Label path;
	@FXML
	private TextArea pathDetails;
	@FXML
	private Button run;
	@FXML
	private Label distance;
	@FXML
	private TextField distDetails;
	Label alertLbl=new Label("Error message");;
	Stage alert = new Stage();
	Font font = Font.font("Comic Sans MS", FontWeight.MEDIUM, FontPosture.REGULAR, 15);
	ImageView sourceImg;
	ImageView destImg;
	// Event Listener on Button[#run].onAction
	@FXML
	public void run(ActionEvent event) {
		String v1City = srcCombo.getValue();
		String v2City = destCombo.getValue();
		if (v1City != null && v2City != null) {
			Vertex v1 = hash.get(v1City);
			Vertex v2 = hash.get(v2City);
			if (v1 != null && v2 != null) {

				dijekstra(v1, v2);
//				System.out.println(v1.toString());
//				for (int i = 0; i < v1.e.size(); i++) {
//					System.out.print(v1.e.get(i).toString() + "-> ");
//				}
//				System.out.println("-----------------------------------------------------------------------");
//				System.out.println(v2.toString());
			} else {
				alertLbl.setText("Please choose a valid source and destination");
				alert.show();
			}
		} else {
			alertLbl.setText("Please choose both source and destination");
			alert.show();
		}
	}

	// Event Listener on Button[#clear].onAction
	@FXML
	public void clear(ActionEvent event) {
		
		choser = "src";
		if(srcCombo.getValue()==null &&destCombo.getValue()==null &&pathDetails.getText()==""&&distDetails.getText()=="") {
			alertLbl.setText("There is nothing to clear");
			alert.show();
		}
		srcCombo.setValue(null);
		destCombo.setValue(null);
		pathDetails.setText("");
		distDetails.setText("");
		pp.clear();

		int childSize = imgAnchor.getChildren().size();
//		System.out.println(childSize);
		for (int i = childSize - 1; i >= 0; i--) {
			if (imgAnchor.getChildren().get(i) instanceof Line) {
				imgAnchor.getChildren().remove(i);
			}
		}
//		System.out.println(hash.size());
		for (Map.Entry<String, Vertex> entry : hash.entrySet()) {
			Vertex value = entry.getValue();
			value.visited = false;
			value.distance = Double.MAX_VALUE;
			value.prev = null;
//			System.out.println(value.toString());
		}
		imgAnchor.getChildren().removeAll(sourceImg,destImg);
	}

	@FXML
	private void initialize() {
		
		pathDetails.setText(cityName);
		
		sourceImg= new ImageView("Screenshot_2024-01-09_194309-removebg-preview.png");
		sourceImg.setFitHeight(20);
		sourceImg.setFitWidth(20);
		destImg= new ImageView("Screenshot_2024-01-09_194552-removebg-preview.png");
		destImg.setFitHeight(20);
		destImg.setFitWidth(15);
		// read file
		File inFile = new File("C:\\Users\\User\\Desktop\\Comp336_Project3\\src\\gazaStrip.txt");
		try (BufferedReader br = new BufferedReader(new FileReader(inFile))) {
			String firstLine = br.readLine().trim();
			num = firstLine.split(" ");
			numOfVertex = Integer.parseInt(num[0]);
			numOfEdges = Integer.parseInt(num[1]);
			String line;
			for (int i = 0; i < numOfVertex; i++) {
				line = br.readLine();
				String[] details = line.split(",");
				cityName = details[0];
				lat = Double.parseDouble(details[1]);
				lng = Double.parseDouble(details[2]);
				Vertex v = new Vertex(cityName, lat, lng);
				v.x = (((lng - minLng) * (wMaxX - wMinX)) / (maxLng - minLng)) + wMinX;
				v.y = (((lat - minLat) * (wMaxY - wMinY)) / (maxLat - minLat)) + wMinY;
//				vertices.add(v);
				hash.put(cityName, v);
				HBox hb = new HBox(2);
				Text city = new Text(v.city);

				city.setFont(Font.font(7));

				if (v.city.contains("Cross")) {
					city.setText("");
//					city.getText().replaceAll("Cross", "");
//					city.font
					v.x = v.x;
					v.y = v.y;
					Circle marker = new Circle(v.x, v.y, 0.5, Color.TRANSPARENT);

//					hb.getChildren().addAll(marker, city);
//					hb.setLayoutX(v.x);
//					hb.setLayoutY(v.y);
//
//					imgAnchor.getChildren().add(hb);
					imgAnchor.getChildren().add(marker);
				} else {
//					city.setText("");
					Circle marker = new Circle(v.x, v.y, 3, Color.RED);
					choser = "src";
					marker.setOnMouseClicked(e -> {
						
						HBox parent = (HBox) marker.getParent();
						Text cityname = (Text) parent.getChildren().get(1);
						if (choser == "src") {
							chosen = hash.get(cityname.getText());
							srcCombo.setValue(cityname.getText());
//							System.out.println(chosen.toString());
							sourceImg.toFront();
							choser = "dst";
						} else if (choser == "dst") {
//							imgAnchor.getChildren().removeAll(destImg);
							chosen = hash.get(cityname.getText());
							destCombo.setValue(cityname.getText());
//							System.out.println(chosen.toString());
							destImg.toFront();
							choser = "src";
						}
//						System.out.println(cityname.getText());
					});
					hb.getChildren().addAll(marker, city);
					hb.setLayoutX(v.x);
					hb.setLayoutY(v.y);

					imgAnchor.getChildren().add(hb);

					srcCombo.getItems().add(cityName);
					destCombo.getItems().add(cityName);
					srcCombo.setOnAction(e -> {
						chosen = hash.get(srcCombo.getValue());
						if (chosen != null) {
					        imgAnchor.getChildren().removeAll(sourceImg);
					        sourceImg.setLayoutX(chosen.x - 8);
					        sourceImg.setLayoutY(chosen.y - 18);
					        imgAnchor.getChildren().add(sourceImg);
					        choser = "dst";
					    }
					});
					
					destCombo.setOnAction(e -> {
						chosen = hash.get(destCombo.getValue());
						if (chosen != null) {
					        imgAnchor.getChildren().removeAll(destImg);
					        destImg.setLayoutX(chosen.x - 6);
					        destImg.setLayoutY(chosen.y - 18);
					        imgAnchor.getChildren().add(destImg);
					        choser = "src";
					    }
					});
				}
			}
			for (int i = 0; i < numOfEdges; i++) {
				line = br.readLine();
//				System.out.println(line);
				String[] details = line.split(",");
				Vertex e1 = hash.get(details[0]);
				Vertex e2 = hash.get(details[1]);
				Edge edge = new Edge(0.0, e1, e2);
				edge.setCost(e1, e2);
				e1.e.add(edge);
				Line l = new Line();
				l.setStartX(e1.x);
				l.setStartY(e1.y);
				l.setEndX(e2.x);
				l.setEndY(e2.y);
				l.setStroke(Color.BLACK);
				l.setStrokeWidth(1);
				imgAnchor.getChildren().add(l);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		VBox alertVB = new VBox(10);
		ImageView imgv = new ImageView(new Image("alert-removebg-preview.png"));
		imgv.setFitWidth(60);
		imgv.setFitHeight(60);
		Button ok = new Button("OK");
		alertLbl.setFont(font);
		alertVB.getChildren().addAll(imgv, alertLbl, ok);
		ok.setOnAction(e -> {
			alert.close();
		});
		alertVB.setAlignment(Pos.CENTER);
		Scene alertS = new Scene(alertVB, 400, 200);
		alert.setScene(alertS);
		alert.setTitle("warning message");
	}

	public void dijekstra(Vertex src, Vertex dest) {
		src.distance = 0;

		if (src == dest) {
			pathDetails.setText("There is no path");
			distDetails.setText("0 km");

		} else {
			PriorityQueue<Vertex> pq = new PriorityQueue<>(new Comparator<Vertex>() {

				@Override
				public int compare(Vertex o1, Vertex o2) {
					return Double.compare(o1.distance, o2.distance);
				}
			});
			pq.add(src);
			while (!pq.isEmpty()) {
				Vertex cityy = pq.poll();
				cityy.visited = true;

				if (cityy.city.equals(dest.city)) {
					break;
				}
				for (Edge ed : cityy.e) {
					Vertex v = ed.v2;
					if (!v.visited) {
						double cost1 = ed.cost;
						double distnew = cityy.distance + cost1;
						if (distnew < v.distance) {
							v.distance = distnew;
							v.prev = cityy;

							pq.add(v);
						}
					}
				}

			}

			for (Vertex v = dest; v != null; v = v.prev) {
				pp.add(v);
			}
			pathDetails.setWrapText(true);

			for (int i = pp.size() - 1; i >= 0; i--) {
				if (i != 0) {
					if(pp.get(i).city.contains("Cross")) {
						String cross = pp.get(i).city.replace("Cross", "");
						pathDetails.setText(pathDetails.getText() + cross + "->");

					}else {
					pathDetails.setText(pathDetails.getText() + pp.get(i).city + "->");}
				} else {
					pathDetails.setText(pathDetails.getText() + pp.get(i).city + ".");
				}
			}
			distDetails.setText(dest.distance + " Km");
			for (int i = 0; i < pp.size() - 1; i++) {
				Vertex first = pp.get(i);
				Vertex second = pp.get(i + 1);
				Line line = new Line();
				if (first.city.contains("Cross")) {
					line.setStartX(first.x);
					line.setStartY(first.y);
				}
				if (second.city.contains("Cross")) {
					line.setEndX(second.x);
					line.setEndY(second.y);
				}
				if (!first.city.contains("Cross")) {
					line.setStartX(first.x + 3);
					line.setStartY(first.y + 3);
				}
				if (!second.city.contains("Cross")) {
					line.setEndX(second.x + 3);
					line.setEndY(second.y + 3);
				}
				

				line.setStroke(Color.BLACK);
				line.setStrokeWidth(1);
				imgAnchor.getChildren().add(line);
			}
		}

	}
}