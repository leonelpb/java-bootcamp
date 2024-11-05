import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../core/services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export default class RegisterComponent {
  username: string = '';
  direccion: string = '';
  password: string = '';
  email: string = ''

  constructor(private authService: AuthService, private router: Router) {
  }
  signUp(): void {
    this.authService.signup(this.username, this.password, this.direccion, this.email).subscribe({
      next: (response) => {
        const token = response.token;
        this.router.navigate([''])
      },
      error: (err) => console.error('Sign Up failed', err)
    })
  }
}
