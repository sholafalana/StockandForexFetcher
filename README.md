# Stock and Forex Fetcher
Android Developer NanoDegree Project capstone II

stockandforexfetcher is a simple fully functional android app which I made for the Android Developer Nanodegree program.

With the app, you can:

   * Fetch Financial data from a remote network
   * persist data from saved events
   * Calculates the Pivot point, Resistance and Support price of selected currency
   * Display real time cryptocurrency update
   * Instantly search for any stock, currency, or CFds with the instrument symbol
   * Gets the graphical analysis of any stock, or currency

# How to Work with the Source

This app uses alphavantage.co, and coincap API to retrieve stock data at realtime. You must provide your own API key in order to build the app. When you get it, add it to build.gradle file located in the app module.

## Features

* Present users with a linear layout arrangement of cryptocurrency realtime information .

* Allow users to easily fetch currency price or input a custom price, and calculate the pivot point of currenies

* Implemented Room Database to save trade events data.


* Allow users to interact with the app via a widget: When click the widget title, the app fetches current rates instantly . 
views
* Used adapters and viewholders, and viewpagers to populate list  and graphs.

* Support accessibility for vision-limited users.

* Uses AsyncTask Query method for stock search

* Stored all the string variables in string.xml.

## Libraries

* Material Design
* Dagger 2
* Retrofit
* Socket IO
* Achartengine
* MVVM (Room, ViewModel, LiveData )
* ButterKnife
* Firebase 
* Paging Library

## ScreenShots

![photo5764933885170397324](https://user-images.githubusercontent.com/32399318/59290228-f9b29680-8c6f-11e9-85ff-a66b6b100caa.jpg)
![photo5764933885170397322](https://user-images.githubusercontent.com/32399318/59290229-fa4b2d00-8c6f-11e9-8f74-da917af12195.jpg)
![photo5764933885170397326](https://user-images.githubusercontent.com/32399318/59290230-fae3c380-8c6f-11e9-9353-fc789294aac3.jpg)
![photo5764933885170397328](https://user-images.githubusercontent.com/32399318/59290231-fae3c380-8c6f-11e9-936c-f764dc98ea99.jpg)
![photo5764933885170397327](https://user-images.githubusercontent.com/32399318/59290232-fb7c5a00-8c6f-11e9-8199-3fa583634caa.jpg)
![photo5764933885170397325](https://user-images.githubusercontent.com/32399318/59290235-fcad8700-8c6f-11e9-9372-2b3eb15ba48c.jpg)

