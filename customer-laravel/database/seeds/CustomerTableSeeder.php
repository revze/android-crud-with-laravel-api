<?php

use Illuminate\Database\Seeder;
use App\Customer;

class CustomerTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
      Customer::truncate();
      $faker = \Faker\Factory::create();
      for ($i=0; $i < 20; $i++) {
        Customer::create(['name'=>$faker->name,'birth_date'=>'19-05-1999','gender'=>'Male','phone_number'=>$faker->phoneNumber]);
      }
    }
}
