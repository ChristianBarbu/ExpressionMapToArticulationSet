# ExpressionMapToArticulationSetConverter

Convert your Expression Map from Cubase to an usable ArticulationSet for Logic Pro X.

<h2> How to use </h2>

<h3> Prerequisites </h3>

1) Java
2) IDE (IntelliJ, Eclipse etc. to open Java Source Code)

<h3> Convert your files </h3>

1) Create a folder "SourceFiles" and put it in the ExpressionMapToAritculationSet folder
2) Create a folder "OutputFiles" and put it in the ExpressionMapToAritculationSet folder
3) Put your Cubase .expressionmap files into the created source folder
4) Run the main-method in Converter.java
5) Now your Logic ArticulationSets are located in the "OutputFiles"

<h3> Notes: </h3>

1) Do name the folders exactly like it is said, otherwise it won't work.
2) Currently the implementation is not very generic, as every file in the input folder will have the "Note On" option enabled after parsing independently from how the file has been configured in Cubase.
3) Currently only Cubase -> Logic Pro X Presets work.
