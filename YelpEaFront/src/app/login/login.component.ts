import { Component, inject } from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatInputModule} from '@angular/material/input';
import {ReactiveFormsModule, FormBuilder, Validators} from '@angular/forms';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  imports: [MatButtonModule, 
    MatCardModule, 
    MatFormFieldModule, 
    MatInputModule, 
    MatCheckboxModule, 
    ReactiveFormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})

export class LoginComponent {
  private formBuilder = inject(FormBuilder);
  private router = inject(Router)

  loginForm = this.formBuilder.group({
    login: ['', Validators.required],
    pswd: ['', Validators.required],
  });

  onSubmit() {
    if (this.loginForm.valid) {
      this.router.navigate(['/accueil']);
    }  
  }
}
