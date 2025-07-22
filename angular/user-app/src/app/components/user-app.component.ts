import { Component } from '@angular/core';
import { UserService } from '../services/user.service';
import { User } from '../models/user';
import { UserComponent } from './user/user.component';
import { UserFormComponent } from './user-form/user-form.component';
import Swal from 'sweetalert2';

@Component({
    selector: 'user-app',
    imports: [UserComponent, UserFormComponent],
    templateUrl: './user-app.component.html'
})
export class UserAppComponent {
    title: string = 'Lista de Usuarios';
    users: User[] = [];
    userSelected: User;
    open: boolean = false;


    constructor(private userService: UserService) {
        this.userSelected = new User();
    }

    ngOnInit() {
        this.userService.findAll().subscribe(users => {
            this.users = users;
        });
    }

    addUser(user: User): void {
        // this.users.push(user);
        if (user.id) {
            this.users = this.users.map(u => u.id === user.id ? { ...user } : u);
            Swal.fire({
                title: "Exito!",
                text: "Usuario actualizado correctamente.",
                icon: "success"
            });
        } else {
            this.users = [...this.users, { ...user }];
            Swal.fire({
                title: "Exito!",
                text: "Usuario agregado correctamente.",
                icon: "success"
            });
        }
        this.setOpen();
    }

    removeUser(userId: number): void {
        // const confirmRemove = confirm('¿Estás seguro de eliminar este usuario?');
            Swal.fire({
                title: "Seguro de eliminar?",
                text: "No podrás revertir esto!",
                icon: "warning",
                showCancelButton: true,
                confirmButtonColor: "#3085d6",
                cancelButtonColor: "#d33",
                confirmButtonText: "Sí, eliminar!",
                cancelButtonText: "Cancelar"
            }).then((result) => {
                if (result.isConfirmed) {
                    this.users = this.users.filter(user => user.id !== userId);
                    this.userSelected = new User(); // Limpia el formulario
                    Swal.fire({
                        title: "Eliminado!",
                        text: "Usuario ha sido eliminado.",
                        icon: "success"
                    });
                    this.open = false; // Cierra el formulario si estaba abierto
                }
            });
            
    }

    updateUser(userRow: User): void {
        this.userSelected = { ...userRow };
        this.open = true; // Abre el formulario para editar
    }

    setOpen(): void {
        this.open = !this.open;
    }
}
