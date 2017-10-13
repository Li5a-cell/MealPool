import controllers.*;

import dao.KeywordDao;
import dao.RecipeDao;
import dao.ScheduleDao;
import dao.UserDao;
import dummy.Dummies;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.assets.*;
import org.h2.jdbcx.JdbcConnectionPool;

import org.jooq.SQLDialect;
import org.jooq.impl.DefaultConfiguration;

public class SimpleApplication extends Application<Configuration> {

	public static void main(String[] args) throws Exception {
		new SimpleApplication().run(args);
	}

	// private static void enableSessionSupport(Environment env) {
	// 	env.servlets().setSessionHandler(new SessionHandler());
	// }

	public static org.jooq.Configuration setupJooq() {
		// For now we are just going to use an H2 Database.  We'll upgrade to mysql later
		// This connection string tells H2 to initialize itself with our schema.sql before allowing connections
		final String jdbcUrl = "jdbc:h2:mem:test;MODE=MySQL;INIT=RUNSCRIPT from 'classpath:schema.sql'";
		JdbcConnectionPool cp = JdbcConnectionPool.create(jdbcUrl, "sa", "sa");

		// This sets up jooq to talk to whatever database we are using.
		org.jooq.Configuration jooqConfig = new DefaultConfiguration();
		jooqConfig.set(SQLDialect.MYSQL); // Lets stick to using MySQL (H2 is OK with this!)
		jooqConfig.set(cp);
		return jooqConfig;
	}

	@Override
	public void initialize(Bootstrap<Configuration> bootstrap) {
		bootstrap.addBundle(new AssetsBundle("/assets", "/assets", "index.html"));
	}

	@Override
	public void run(Configuration cfg, Environment env) {
		// Create any global resources you need here
		org.jooq.Configuration jooqConfig = setupJooq();
		KeywordDao keywordDao = new KeywordDao(jooqConfig);
		RecipeDao recipeDao = new RecipeDao(jooqConfig, keywordDao);
		UserDao userDao = new UserDao(jooqConfig, keywordDao);
		ScheduleDao scheduleDao = new ScheduleDao(jooqConfig);

		// Register all Controllers below.  Don't forget
		// you need class and method @Path annotations!
		env.jersey().register(new ReceiptImageController());
		env.jersey().register(new ScheduleController(scheduleDao, recipeDao, userDao));
		env.jersey().register(new UserController(userDao));

		Dummies.DUMMY_CHEF = userDao.insert("sb2483@cornell.edu", "Chef", "password", "10044", 0, 0, null, null);
		Dummies.DUMMY_EATER = userDao.insert("rzl6@cornell.edu", "Eater", "password", "10044", 0, 0, null, null);
	}
}
