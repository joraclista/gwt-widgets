# gwt-widgets
Additional GWT widgets with showcase


### Build

This is a regular maven project,
just type ```mvn clean package```

### Deploy

  * Build with maven
  * Deploy to tomcat/etc
  * access http://yourHost:yourPort/warFileName  (as main html page is index.html, no need to specify it )
  
  
# Usage

## Code Snippets 
<img src="/pics/snippet.png" alt="screenshot" title="screenshot"  height="340" />

                        
```java
  CodeSnippet snippet = new CodeSnippet(SnippetsBundle.BUNDLE.snippet2().getText()) // basic usage : only source code text is provided. 
  //By default dark theme is used for syntax highlight;
 ```
                        
```java
  CodeSnippet snippet = new CodeSnippet(SnippetsBundle.BUNDLE.snippet1().getText(), SnippetsBundle.BUNDLE.darkCss()) // specify source code text and css
                .withSnippetName("Code Listing in Dark Scheme with line numbers and copy button #1") //specify code snippet title if necessary
                .withLineNumbersEnabled(true) // configure line numbers support
                .withCodeCopyButtonEnabled(true) // display 'copy to clipboard' button
```

```java
  // specify another syntax highlight theme via css
  CodeSnippet snippet = new CodeSnippet(SnippetsBundle.BUNDLE.snippet2().getText(), SnippetsBundle.BUNDLE.lightCss()) 
                        .withSnippetName("Code Listing Light Scheme #2")));
```


## Checkboxes
<img src="/pics/checkboxes.png" alt="screenshot" title="screenshot"  height="140" />

```java
  CheckBox cb = new CheckBox("This is Option #1"); // basic usage
  CheckBox cb2 = new CheckBox("This is Option #2")
      .withChecked(true) // specify if it's checked already
      .withEnabled(false); // disabled checkbox
  cb.addValueChangeHandler(event -> Window.alert("Clicked: " + event.getValue())); // add handler
  CheckBox cb3 = new CheckBox(CheckBoxBundle.BUNDLE.rectangledCheckboxCss(), "This is Option #3"); // specify other css
```

## RadioGroups
<img src="/pics/radiobuttons.png" alt="screenshot" title="screenshot"  height="140" />

```java
  VerticalPanel vp = new VerticalPanel();
  RadioGroup radioGroup = new RadioGroup(); // create group
  //  Or specify other css for the radio group: 
  //  RadioGroup radioGroup = new RadioGroup(RadioButtonBundle.BUNDLE.rectangledRadioButtonCss());
  Arrays.asList("Milk", "Chocolate", "Meat", "Bread")
    .stream()
    .map(label -> radioGroup.createButton(label, true))  //basic usage
    .forEach(radioButton -> vp.add(radioButton));
    
  radioGroup.addValueChangeHandler(event -> Window.alert("Selected: " + event.getValue())); // add handler
```

## Calendar
<img src="/pics/calendar.png" alt="screenshot" title="screenshot"  height="200" />

```java
  Calendar calendar = new Calendar(); // basic usage. Also accepts css as constructor param
  calendar.addValueChangeHandler(event -> Window.alert("Selected: " + event.getValue())); // add handler
```

## Notifications
<img src="/pics/notifications.png" alt="screenshot" title="screenshot"  height="200" />

```java
  // basic usage. Creates notification panel with message, can be added to other panels
  Notification notification = new Notification("This is warning")
                        .withType(NotificationType.WARNING)
                        .withArrowPosition(ArrowPosition.TOP); 
  
  // Shows notification in a popup
  Notification notification2 = new Notification("INFO message")
                .withType(NotificationType.INFO)
                .withPopupPosition(Position.TOP_RIGHT)
                .withArrowPosition(ArrowPosition.LEFT)
                .withPopupCloseButtonVisibility(true) //show or not 'close' button
                .withPopupCloseOnBackgroundClick(true) // if true - popup can be closed via background click
                .show();
```
