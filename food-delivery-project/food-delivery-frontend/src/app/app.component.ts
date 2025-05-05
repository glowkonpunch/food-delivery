
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  template: \`
  <nav>
    <a routerLink="/foods">Меню</a> |
    <a routerLink="/login">Вход</a> |
    <a routerLink="/register">Регистрация</a>
  </nav>
  <router-outlet></router-outlet>
  \`
})
export class AppComponent { }
