import { Component, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { User } from '../../models/user';
import { FormsModule, NgForm } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'user-form',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './user-form.component.html'
})
export class UserFormComponent {
  @Input() user: User;

  @Output() newUserEventEmitter: EventEmitter<User> = new EventEmitter();

  @ViewChild('userForm') userForm!: NgForm;

  constructor() {
    this.user = new User();
  }

  onSubmit(): void {
    if (this.userForm.valid) {
      this.newUserEventEmitter.emit(this.user);
      console.log('User: ', this.user);
    }
    this.userForm.resetForm(); // Limpia el formulario
  }

  onClear(ngForm: NgForm): void {
    ngForm.resetForm(); // o this.userForm.resetForm();
    this.user = new User(); // Limpia el objeto user
  }
}
