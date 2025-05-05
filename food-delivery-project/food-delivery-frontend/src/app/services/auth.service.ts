
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

@Injectable({providedIn:'root'})
export class AuthService{
  private api='http://localhost:8080/api/auth';
  token:string|null = localStorage.getItem('token');

  constructor(private http:HttpClient){}

  login(username:string,password:string):Observable<any>{
    return this.http.post<any>(this.api+'/login',{username,password})
      .pipe(tap(res=>{localStorage.setItem('token',res.token); this.token=res.token;}));
  }
  register(username:string,email:string,password:string):Observable<any>{
    return this.http.post<any>(this.api+'/register',{username,email,password});
  }
  isLogged(){return !!localStorage.getItem('token');}
}
