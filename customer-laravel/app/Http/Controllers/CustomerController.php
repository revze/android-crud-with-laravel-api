<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

use App\Http\Requests;
use App\Customer;

class CustomerController extends Controller
{
    public function generateId()
    {
      $response = [];
      $response['success'] = 1;

      $response['id'] = str_random(30);
      return response()->json($response);
    }

    public function index()
    {
      $response = [];
      $response['success'] = 1;
      // $response['message'] =
      $response['customers'] = Customer::orderBy('id','desc')->get();
      // $customers = Customer::orderBy('id','desc')->get();
      return response()->json($response);
    }

    public function show(Request $r)
    {
      $response = [];
      $response['success'] = 1;

      $id = $r->input('id');
      $response['customers'] = Customer::find($id);
      // $customers = Customer::find($id);
      return response()->json($response);
    }

    public function store(Request $r)
    {
      $response = [];
      // $id = $r->input('id');
      $name = $r->input('name');
      $birth_date = $r->input('birth_date');
      $gender = $r->input('gender');
      $phone_number = $r->input('phone_number');

      $customer = new Customer;
      // $customer->id = $id;
      $customer->name = $name;
      $customer->birth_date = $birth_date;
      $customer->gender = $gender;
      $customer->phone_number = $phone_number;
      $customer->save();

      $response['success'] = 1;
      $response['message'] = 'Customer successfully created.';

      return response()->json($response);
    }

    public function edit(Request $r)
    {
      $response = [];
      $response['success'] = 1;

      $id = $r->input('id');
      $response['customers'] = Customer::find($id);
      // $customers = Customer::find($id);
      return response()->json($response);
    }

    public function update(Request $r)
    {
      $response = [];
      $id = $r->input('id');
      $name = $r->input('name');
      $birth_date = $r->input('birth_date');
      $gender = $r->input('gender');
      $phone_number = $r->input('phone_number');

      $customer = Customer::find($id);
      // $customer->id = $id;
      $customer->name = $name;
      $customer->birth_date = $birth_date;
      $customer->gender = $gender;
      $customer->phone_number = $phone_number;
      $customer->save();

      $response['success'] = 1;
      $response['message'] = 'Customer successfully updated.';

      return response()->json($response);
    }

    public function destroy(Request $r)
    {
      $response = [];
      $response['success'] = 1;
      $response['message'] = 'Customer successfully deleted.';

      $id = $r->input('id');
      $customer = Customer::find($id);
      $customer->delete();

      return response()->json($response);
    }
}
