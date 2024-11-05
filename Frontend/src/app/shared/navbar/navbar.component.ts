import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../core/services/auth.service';
import { CartComponent } from "../../features/cart/cart.component";
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, CartComponent,RouterModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss'
})
export class NavbarComponent {
  isMenuOpen=false;
  isProfileOpen=false;
  isLoggedIn=false;
  isCartOpen=false;

  constructor(private authService: AuthService){}
  ngOnInit() {
    this.isLoggedIn = this.authService.isAuthenticated();
  }
  ///Toggles
  toggleMenu(){
    this.isMenuOpen = !this.isMenuOpen;
  }
  toggleProfile(){
    this.isProfileOpen = !this.isProfileOpen;
  }
  toggleCart(){
    this.isCartOpen = !this.isCartOpen;
  }

  logIn() {
    this.isLoggedIn = true;
  }

  logOut() {
    this.authService.logout();
    this.isLoggedIn = false;
    this.isProfileOpen = false;
  }
}
