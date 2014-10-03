package source;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class UserResourceApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		 classes.add(UserResource.class);

		return classes;
	}
	
}
