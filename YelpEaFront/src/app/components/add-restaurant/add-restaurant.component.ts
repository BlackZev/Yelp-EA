import { Component, inject } from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import {ReactiveFormsModule, FormBuilder, Validators} from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-restaurant',
  imports: [MatButtonModule, 
    MatCardModule, 
    MatFormFieldModule, 
    MatInputModule, 
    MatCheckboxModule, 
    MatSelectModule,
    ReactiveFormsModule
  ],
  templateUrl: './add-restaurant.component.html',
  styleUrl: './add-restaurant.component.scss'
})

export class AddRestaurantComponent {
  private formBuilder = inject(FormBuilder);
  private router = inject(Router)

  addRestaurant = this.formBuilder.group({
    name: ['', Validators.required],
    categorie: ['', Validators.required],
    description: [''],
    phone: [''],
    adress: [''],
  });

  onSubmit() {
    if (this.addRestaurant.valid) {
      this.router.navigate(['/accueil']);
    }  
  }
}
