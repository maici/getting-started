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

### Json structure

The style of the pcm can be customize mainly thought 2 json objects :

* Style : define the css style.
```json
{
    "style": {
      "color": "#000000",
      "backgroundColor": "#000000",
      "fontWeight": "bold",
      "fontStyle": "italic",
      "font": "Arial",
      "fontSize": "10"
    }
}
```
* Operation: an operation change the style of the pcm when the condition specified is true
```json
{
    "id": "op_sup_0",
    "type": "comparison",
    "values": {
      "operation": ">",
      "operand": "0"
    },
    "style": {
      "color": "#000000"
    }
}
```
3 operations are possible :
* comparison : compare numeric values
```json
{
    "id": "op_inf_0",
    "type": "comparison",
    "values": {
      "operation": "<",
      "operand": "0"
    },
    "style": {
      "color": "#000000"
    }
}
```
* range : define an numeric range
```json
{
    "id": "op_range_0_1",
    "type": "range",
    "values": {
      "minValue": "0",
      "maxValue": "1"
    },
    "style": {
      "color": "#000000"
    }
}
```
* string-comparison: match a string
```json
{
    "id": "op_match_test",
    "type": "string-comparison",
    "values": {
      "match": "test"
    },
    "style": {
      "color": "#000000"
    }
}
```
The json file is structured around 3 customizable elements :

* the pcm : style and/or operations that affect all the pcm

```json
"pcm": {
    "title": "pcm title",
    "description": "pcm description",
    "style": {
      "color": "#000000",
      "backgroundColor": "#000000",
      "fontWeight": "bold",
      "fontStyle": "italic",
      "font": "Arial",
      "fontSize": "10"
    },
    "operations": [
      {
        "id": "op_sup_0",
        "type": "comparison",
        "values": {
          "operation": ">",
          "operand": "0"
        },
        "style": {
          "color": "#000000"
        }
      }
    ]
}
```

* the features (pcm columns) : style and/or operations that affect all the colums of the pcm

```json
"pcm":{
    "title": "pcm title",
    "description": "pcm description",
    "features": {
      "style": {
        "color": "#000000",
        "backgroundColor": "#000000",
        "fontWeight": "bold",
        "fontStyle": "italic",
        "font": "Arial",
        "fontSize": "10"
      },
      "operations": [
        {
          "id": "op_sup_0",
          "type": "comparison",
          "values": {
            "operation": ">",
            "operand": "0"
          },
          "style": {
            "color": "#000000"
          }
        }
      ]
    }
  }
```

* the products (pcm lines) : style and/or operation that affect all the lines of the pcm

```json
"pcm":{
    "title": "pcm title",
    "description": "pcm description",
    "products": {
      "style": {
        "color": "#000000",
        "backgroundColor": "#000000",
        "fontWeight": "bold",
        "fontStyle": "italic",
        "font": "Arial",
        "fontSize": "10"
      },
      "operations": [
        {
          "id": "op_sup_0",
          "type": "comparison",
          "values": {
            "operation": ">",
            "operand": "0"
          },
          "style": {
            "color": "#000000"
          }
        }
      ]
    }
  }
```

**Note :** You can also define a style and/or an operation for a specific feature(column) or product(line):
```json
"features": {
      "elements": [
        [
          "column name",
          {
            "style": {
              "color": "#000000",
              "backgroundColor": "#000000",
              "fontWeight": "bold",
              "fontStyle": "italic",
              "font": "Arial",
              "fontSize": "10"
            },
            "operations": [
              {
                "id": "op_sup_0",
                "type": "comparison",
                "values": {
                  "operation": ">",
                  "operand": "0"
                },
                "style": {
                  "color": "#000000"
                }
              }
            ]
          }
        ]
      ]
    }
```


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

* First of all, you need a pcm file and your parameters in json format.

```java
File pcmFile = new File("./src/test/pcms/example.pcm");
File jsonFile = new File("./src/test/params/example.json");
```

* The you need to instantiate a pcm object

```java
PCMLoader loader = new KMFJSONLoader();
PCMContainer pcmContainer = loader.load(pcmFile).get(0);
```

* You will also need to instantiate the pcm parameters object (json file)

```java
JsonParamsLoader jsonParamsLoader = new JsonParamsLoader(jsonFile);
PcmParams pcmParams = jsonParamsLoader.load();
```

* Finally you will have to create the exporter and launch the export

```java
CustomHtmlExporter exporter = new CustomHtmlExporter();
exporter.export(pcmContainer, pcmParams, "./out");
```