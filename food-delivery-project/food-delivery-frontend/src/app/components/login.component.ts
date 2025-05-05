
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-login',
  template: \`
    <h2>Вход</h2>
    <form (ngSubmit)="login()">
      <label>Username: <input [(ngModel)]="username" name="username"></label><br>
      <label>Password: <input [(ngModel)]="password" name="password" type="password"></label><br>
      <button type="submit">Login</button>
    </form>
    <div *ngIf="error">{{error}}</div>
  \`
})
export class LoginComponent {
  username = '';
  password = '';
  error = '';

  constructor(private auth: AuthService, private router: Router) {}

  login() {
    this.auth.login(this.username, this.password).subscribe({
      next: () => this.router.navigate(['/foods']),
      error: () => this.error = 'Невалидни данни'
    });
  }
}
