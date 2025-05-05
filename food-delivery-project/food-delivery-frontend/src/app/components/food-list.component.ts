
import { Component, OnInit } from '@angular/core';
import { FoodService } from '../services/food.service';
import { Food } from '../models';

@Component({
  selector: 'app-food-list',
  template: \`
    <h2>Меню</h2>
    <div *ngFor="let food of foods">
      {{food.name}} - {{food.price}} лв.
    </div>
  \`
})
export class FoodListComponent implements OnInit {
  foods: Food[] = [];
  constructor(private foodService: FoodService){}
  ngOnInit(){
    this.foodService.getFoods().subscribe(fs => this.foods = fs);
  }
}
