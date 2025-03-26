import { ChangeDetectionStrategy, Component } from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {MatChipsModule} from '@angular/material/chips';
import {MatSelectModule} from '@angular/material/select';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatFormFieldModule} from '@angular/material/form-field';

@Component({
  selector: 'app-list-restaurants',
  imports: [MatButtonModule, MatCardModule, MatChipsModule, MatSelectModule, MatFormFieldModule, MatProgressBarModule],
  templateUrl: './list-restaurants.component.html',
  styleUrl: './list-restaurants.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})

export class ListRestaurantsComponent {
  title = "Mon restaurant"
  categorie = "Ma catégorie"
  description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eu posuere massa, id tristique nulla. In interdum auctor nisl sed facilisis. Maecenas quis velit non mi sollicitudin laoreet eu a tortor. Sed maximus sed tortor vel mattis. Ut euismod pellentesque mi, eu lacinia odio congue eu."
  phone = "Tél : 00 00 00 00 00"
  mail = "Mail : loremipsum@gmail.com"
  adress = "Adresse : 0 rue de Lorum"
  customerNote = "Sélectionnez une note"
  globalNote = "0/5"

  nbCards = 5;
}
