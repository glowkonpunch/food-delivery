
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Food } from '../models';

@Injectable({providedIn:'root'})
export class FoodService{
  private api='http://localhost:8080/api/foods';
  constructor(private http:HttpClient){}
  getFoods():Observable<Food[]>{ return this.http.get<Food[]>(this.api); }
}
