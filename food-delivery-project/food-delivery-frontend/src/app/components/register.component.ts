
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-register',
  template: \`
    <h2>Регистрация</h2>
    <form (ngSubmit)="register()">
      <label>Username: <input [(ngModel)]="username" name="username"></label><br>
      <label>Email: <input [(ngModel)]="email" name="email"></label><br>
      <label>Password: <input [(ngModel)]="password" name="password" type="password"></label><br>
      <button type="submit">Register</button>
    </form>
    <div *ngIf="msg">{{msg}}</div>
  \`
})
export class RegisterComponent {
  username=''; email=''; password=''; msg='';

  constructor(private auth: AuthService, private router: Router) {}

  register(){
    this.auth.register(this.username,this.email,this.password).subscribe({
      next: () => {this.msg='Регистрация успешна'; this.router.navigate(['/login']);},
      error: () => this.msg='Грешка при регистрация'
    });
  }
}
