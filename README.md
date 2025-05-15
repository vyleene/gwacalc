# GWAveraged 🎓📊
###### [NOTE] Final output for the CCC102 course.

Welcome to **GWAveraged**, the ultimate Grade Weighted Average (GWA) calculator that makes managing your academic life as easy as pie (or GPA)! Whether you're a student trying to keep track of your grades or just someone who loves calculating GPAs for fun (we don't judge), this app has got you covered.

## Features ✨

- **Dark Mode & Light Mode**: Because your eyes deserve comfort while you calculate your academic destiny.
- **Dynamic GPA Calculation**: Add subjects, assign weights, and let the app do the math for you. No more manual calculations!
- **Custom Grading Systems**: Supports custom grading scales for each subject. Yes, even that one professor's weird grading system.
- **Interactive GUI**: A sleek and intuitive interface built with Swing and FlatLaf themes. It's so smooth, you'll want to calculate GPAs just for fun.

## Gallery 📷
### Dark Mode 🌙

  No more flashbangs during the night

  ---
  <p>
    <img src="https://github.com/vyleene/gwacalc/blob/main/docs/images/main_dark.png" height="400px" >
    <img src="https://github.com/vyleene/gwacalc/blob/main/docs/images/sys_dark.png" >
  </p>
  
### Light Mode ☀️

  Unless you are one of the people who likes it
  
  ---
  <p>
    <img src="https://github.com/vyleene/gwacalc/blob/main/docs/images/main_light.png" height="400px" >
    <img src="https://github.com/vyleene/gwacalc/blob/main/docs/images/sys_light.png" >
  </p>


## Configuration ⚙️
The `config.json` file allows you to customize the app:
- **Grading System**: Add or modify grading scales for each subject.

```
{
    ...
    "subjects": {
        "CCC102": [
            [98.0, 100.0, 1.0],
            [95.0, 97.99, 1.25],
            ...
        ]
    }
}
```

## How It Works 🛠️

1. **Launch the App**: Run the `GWAveraged` application, and the GUI will pop up like magic.
2. **Customize Grading Systems**: Modify grading scales in the Grading System Panel to match your institution's requirements.
3. **Add Subjects**: Use the GPA Insert Panel or GPA Conversion Panel to add subjects and their grades.
4. **Calculate GWA**: Hit the "CALCULATE GWA" button and watch the magic happen.


