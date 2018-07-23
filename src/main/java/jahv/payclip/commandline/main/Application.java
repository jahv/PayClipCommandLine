package jahv.payclip.commandline.main;

import jahv.payclip.commandline.controller.CommandController;
import jahv.payclip.commandline.controller.CommandControllerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@ComponentScan(basePackages = "jahv.payclip.commandline")
@PropertySource(value = {"classpath:application.properties"})
public class Application {

    private CommandControllerFactory commandsFactory;

    @Autowired
    public Application(CommandControllerFactory commandsFactory) {
        this.commandsFactory = commandsFactory;
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        Application p = context.getBean(Application.class);

        p.start(args);
    }

    private void start(String[] args) {
//        try {
            CommandController c = commandsFactory.getCommand(args);

            c.build(args).execute();

            System.out.println(c.printResult());
//        }catch (ArgumentsException e) {
//            Helpers.displayHelp();
//        }
    }

}
