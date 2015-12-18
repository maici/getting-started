# OpenCompare - HTMLExporter configurable

This project contains development artifacts used to perform the export of a product comparison matrices (PCM) in the HTML format according to a configuration of the graphic aspects.
the user will seize different parameters which he wishes to personalize for the pcm via a Configuration file type JSON.

# Development tools

## Json

We choose Json Format to edit the parameters for many reasons :  

* The simple syntax and tree structure permit to maintain lightness and efficacity while other formats likes HTML or XML are composed with tags and are more complex to treat.  
* It's easy to integrate it inside a graphic interface. Indeed, this format is used in many languages likes JavaScript, PHP, Perl, Python, Ruby, Java...  
* This format is not difficult to heard by the users et to interprate by the machins.  

To exploit Json format with Java, we retained the Google-Gson library which is available on the GitHub Platform.  

## Genesis

Genesis is a Java library which permit to generate CSS code. We choose this tool because of its usage looked not difficult and performant for our usage.  
After parsing the Json file, we use it to create css classes which contain all the properties witch the user want to modify.

## Bootstrap

Bootstrap is an open-source Javascript framework, it is a combination of HTML, CSS, and Javascript code designed to help build user interface components. Bootstrap was also programmed to support both HTML5 and CSS3


# Project Architecture

This project is composed by 3 packages :  

## CustomHtmlExporter

Heart of our application, it contains the class which generate all the HTML and CSS files of an exportation.

This package depend of 3 other packages :

* org.opencompare.api.java : contains classes which permit to read and modify the pcm object.  
* org.opencompare.jsonParser : contains classes which permit to parse the parameters in json format.
* org.opencompare.cssGenerator : contains classes which permit the css generation.  

## JsonParser

JsonParser depends of the Google-Gson library.  
This package contains the classes which permit to convert a Json file to a specific object.  
This object is necessary to be converted in the next step in css.

## cssGenerator

cssGenerator depends of the Genesis library.  
This package contains the classes which permit the generation of the css file using the Json object created in the package JsonParser.  

# Licence

open source

# Running

First of all, you add a pcm in the folder 'pcm'.  
Then in the folder 'params',  you change the parameters file with your style : Style for products, for features, for one or a group of cells , or a style for the hole matrice.  
After that, in /src/test/java/org/opencompare/customHtmlExporter/customHtmlExporterTest.java you have to add the two files(json and pcm).
Then indicate your Html file like ("./out/pcm_oc.html").
Execute the class.
Open your html file to see the result.
 