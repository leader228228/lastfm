package birintsev.lastfm.view.console;

import birintsev.lastfm.restclient.RESTClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.Scanner;

public class ConsoleView implements CommandLineRunner {

    private RESTClient restClient;

    public ConsoleView(@Autowired RESTClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public void run(String... args) throws Exception {
        boolean exitCondition = false;
        Scanner scanner = new Scanner(System.in);
        while (!exitCondition) {
            parseCommand(scanner.nextLine());
        }
    }

    private void parseCommand(String cmd) {
        // todo
    }
}
