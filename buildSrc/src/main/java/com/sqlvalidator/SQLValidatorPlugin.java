package com.sqlvalidator;

import com.sqlvalidator.Extensions.ValidatorTaskExtension;
import com.sqlvalidator.Tasks.ValidatorTask;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

import java.io.File;

/**
 * The plugin class is where the task is registered and files are accessed.
 */
public class SQLValidatorPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {

        project.getExtensions().create("SQLValidation", ValidatorTaskExtension.class);
        project.getTasks().register("ValidatorTask", ValidatorTask.class);
    }
}





