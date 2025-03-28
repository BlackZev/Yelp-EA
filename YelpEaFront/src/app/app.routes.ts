import { Routes } from '@angular/router';
import { ListRestaurantsComponent } from './list-restaurants/list-restaurants.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { AddRestaurantComponent } from './add-restaurant/add-restaurant.component';
import { LoginComponent } from './login/login.component';

export const routes: Routes = [
    {path: 'accueil', component:ListRestaurantsComponent},
    {path: 'accueil/addRestaurant', component:AddRestaurantComponent},
    {path: 'adminpanel', component:AdminPanelComponent},
    {path: 'login', component:LoginComponent},
    {path: '', redirectTo:'login', pathMatch:'full'},
    {path: '**', component:NotFoundComponent}
];
