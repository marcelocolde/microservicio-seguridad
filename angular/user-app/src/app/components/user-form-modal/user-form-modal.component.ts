import { Component, Output, EventEmitter, Input, ViewChild } from '@angular/core';
import { User } from '../../models/user';
import { FormsModule, NgForm } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'user-form-modal',
  imports: [FormsModule, CommonModule],
  templateUrl: './user-form-modal.component.html'
})
export class UserFormModalComponent {

  @Input() user: User;

  @Output() newUserEventEmitter: EventEmitter<User> = new EventEmitter();

  @ViewChild('userForm') userForm!: NgForm;

  @Output() openModalEmitter: EventEmitter<boolean> = new EventEmitter();

  constructor() {
    this.user = new User();
  }

  close(): void {
    this.openModalEmitter.emit(false);
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
