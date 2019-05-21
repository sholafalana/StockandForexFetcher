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
* Achartengine
* Room Database
* ButterKnife
* Firebase 

