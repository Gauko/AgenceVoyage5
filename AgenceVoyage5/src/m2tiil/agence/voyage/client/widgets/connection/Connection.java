package m2tiil.agence.voyage.client.widgets.connection;

import m2tiil.agence.voyage.client.GreetingService;
import m2tiil.agence.voyage.client.GreetingServiceAsync;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;


public class Connection extends Composite {

	protected String token;
	private final GreetingServiceAsync service = GWT
			.create(GreetingService.class);
	
	public interface UiUserConnection extends UiBinder<Widget, Connection> {

	}
	
	private static UiUserConnection uiUserConnection = GWT.create(UiUserConnection.class);
	
	@UiField Label login;
	@UiField Label password;
	@UiField Button connect;
	@UiField TextBox loginName;
	@UiField TextBox paswd;
	
	public Connection() {
		initWidget(uiUserConnection.createAndBindUi(this));
	}
	
	@UiHandler("connect")
	public void acceptConnexion(ClickEvent e){
		if(loginName.getValue().length() > 0 && paswd.getValue().length() >0){
			service.login(loginName.getText(), paswd.getText(), new AsyncCallback<String>() {
				
				@Override
				public void onSuccess(String result) {
					token = result;
				}
				
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("User not exist");
					
				}
			});
		}else if(loginName.getValue().length() == 0 || paswd.getValue().length() == 0){
			Window.alert("Verifier si les champs sont remplis");
		}
	}
	
}