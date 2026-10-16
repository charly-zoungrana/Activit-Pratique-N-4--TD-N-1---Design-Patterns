package net.zoungrana.question2;

public class Test {
    public static void main(String[] args) {

        Plugin pluginA=PluginFactory.creerPlugin("A");
        pluginA.appliquerAlgo();

        Plugin pluginB=PluginFactory.creerPlugin("B");
        pluginB.appliquerAlgo();


    }
}
