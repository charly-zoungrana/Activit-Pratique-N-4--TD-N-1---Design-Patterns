package net.zoungrana.question2;

public class PluginFactory {
    public static Plugin creerPlugin(String pluginType){
        return switch (pluginType) {
            case "A" -> new PluginA();
            case "B" -> new PluginB();
            default -> throw new IllegalArgumentException("Le type de plugin n'existe pas");
        };
    }
}
