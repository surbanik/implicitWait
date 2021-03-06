package yaml;

import driverFactory.Browser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Loader {

    public List<EnvDetails> createEnvList() throws IOException {
        YamlReader yamlReader = new YamlReader();
        EnvDetails test = yamlReader.getConfig().getEnvironment().getTest();
        EnvDetails dev = yamlReader.getConfig().getEnvironment().getDev();
        List<EnvDetails> envList = new ArrayList<>();
        envList.add(test);
        envList.add(dev);
        return envList;
    }


    public Loader() throws IOException {
        for (EnvDetails env : createEnvList()){
            if (env.getActive().equals("y")){
                System.setProperty("webURL", env.getWebUrl());
                System.setProperty("title",env.getTitle());
                System.setProperty("browser",env.getBrowser());
                System.setProperty("userEmail",env.getUserEmail());
                System.setProperty("userPassword",env.getUserPassword());
                System.setProperty("loginFailMessage",env.getLoginFailMessage());
                break;
            }
        }
    }

    public Browser getBrowser(){
        String yamlBrowser = System.getProperty("browser");
        switch(yamlBrowser){
            case "chrome":
                return Browser.CHROME;
            case "firefox":
                return Browser.FIREFOX;
            case "ie":
                return Browser.IE;
            default :
                return Browser.EDGE;
        }
    }
}
