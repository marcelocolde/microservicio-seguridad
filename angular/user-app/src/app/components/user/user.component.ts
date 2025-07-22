import { Component, EventEmitter, Input, Output } from '@angular/core';
import { User } from '../../models/user';

@Component({
  selector: 'user',
  imports: [],
  templateUrl: './user.component.html'
})
export class UserComponent {
    @Input() users: User[] = [];  

    @Output() removeUserEmitter: EventEmitter<number> = new EventEmitter();

    @Output() updateUserEmitter: EventEmitter<User> = new EventEmitter();

    onRemoveUser(userId: number): void {
        this.removeUserEmitter.emit(userId);
    }

    onSelectUser(user: User): void {
        this.updateUserEmitter.emit(user);
    }
}
