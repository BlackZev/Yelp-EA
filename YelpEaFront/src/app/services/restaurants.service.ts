import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environment/development';
import { Restaurant } from '../models/RestaurantModel';

@Injectable({
  providedIn: 'root'
})
export class RestaurantsService {
  private apiUrl = environment.apiEndPoint;

  constructor(private http: HttpClient) { }

  getAll(): Observable<Restaurant[]> {
    const currentUrl = this.apiUrl + 'restaurant/all';
    return this.http.get<Restaurant[]>(currentUrl);
  }
}