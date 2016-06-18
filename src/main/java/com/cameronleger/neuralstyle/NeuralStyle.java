package com.cameronleger.neuralstyle;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class NeuralStyle {
    private static String executable = "th";
    private static File neuralStylePath = new File("/home/cameron/neural-style");
    private File styleImage;
    private File contentImage;
    private File outputFolder;

    public static String getExecutable() {
        return executable;
    }

    public static void setExecutable(String executable) {
        NeuralStyle.executable = executable;
    }

    public static File getNeuralStylePath() {
        return neuralStylePath;
    }

    public static void setNeuralStylePath(File neuralStylePath) {
        NeuralStyle.neuralStylePath = neuralStylePath;
    }

    public File getStyleImage() {
        return styleImage;
    }

    public void setStyleImage(File styleImage) {
        this.styleImage = styleImage;
    }

    public File getContentImage() {
        return contentImage;
    }

    public void setContentImage(File contentImage) {
        this.contentImage = contentImage;
    }

    public File getOutputFolder() {
        return outputFolder;
    }

    public void setOutputFolder(File outputFolder) {
        this.outputFolder = outputFolder;
    }

    public boolean checkArguments() {
        return FileUtils.checkFileExists(getStyleImage()) &&
                FileUtils.checkFileExists(getContentImage()) &&
                FileUtils.checkFolderExists(getNeuralStylePath()) &&
                FileUtils.checkFolderExists(getOutputFolder());
    }

    public File getOutputImage() {
        if (!checkArguments())
            return null;
        File styleOutputFolder = new File(getOutputFolder(), FileUtils.getFileName(getStyleImage()));
        if (!styleOutputFolder.exists() && !styleOutputFolder.mkdir())
            return null;
        return new File(styleOutputFolder, FileUtils.getFileName(getContentImage()) + ".jpg");
    }

    public String[] buildCommand() {
        return new String[] {
                getExecutable(),
                "neural_style.lua",
                "-style_image",
                getStyleImage().getAbsolutePath(),
                "-content_image",
                getContentImage().getAbsolutePath(),
                "-output_image",
                getOutputImage().getAbsolutePath()
        };
    }
}
