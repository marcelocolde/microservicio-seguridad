import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private users: User[] = [{
    id: 1,
    name: 'John',
    lastname: 'Doe',
    username: 'johndoe',
    email: 'johndoe@example.com',
    password: 'password123'
  }, {
    id: 2,
    name: 'Jane',
    lastname: 'Smith',
    username: 'janesmith',
    email: 'janesmith@example.com',
    password: 'password456'
  }];

  findAll(): Observable<User[]> {
    return of(this.users);
  }

}
