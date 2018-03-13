# gwt-widgets
Additional GWT widgets with showcase

# Usage

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
