import { Routes } from '@angular/router';
import { ListRestaurantsComponent } from '././components/list-restaurants/list-restaurants.component';
import { NotFoundComponent } from '././components/not-found/not-found.component';
import { AdminPanelComponent } from '././components/admin-panel/admin-panel.component';
import { AddRestaurantComponent } from '././components/add-restaurant/add-restaurant.component';
import { LoginComponent } from '././components/login/login.component';

export const routes: Routes = [
    {path: 'accueil', component:ListRestaurantsComponent},
    {path: 'accueil/addRestaurant', component:AddRestaurantComponent},
    {path: 'adminpanel', component:AdminPanelComponent},
    {path: 'login', component:LoginComponent},
    {path: '', redirectTo:'login', pathMatch:'full'},
    {path: '**', component:NotFoundComponent}
];
