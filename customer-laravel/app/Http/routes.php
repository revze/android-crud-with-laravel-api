<?php

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It's a breeze. Simply tell Laravel the URIs it should respond to
| and give it the controller to call when that URI is requested.
|
*/

Route::get('generate-id','CustomerController@generateId');
Route::get('/','CustomerController@index');
Route::get('show','CustomerController@show');
Route::get('store','CustomerController@store');
Route::get('edit','CustomerController@edit');
Route::get('update','CustomerController@update');
Route::get('destroy','CustomerController@destroy');
