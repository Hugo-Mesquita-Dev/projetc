// Cart Manager
class CartManager {
    constructor() {
        this.cart = JSON.parse(localStorage.getItem('cart')) || [];
        this.user = JSON.parse(localStorage.getItem('user')) || null;
        this.address = JSON.parse(localStorage.getItem('address')) || {
            endereco: '',
            cidade: '',
            cep: ''
        };
    }

    addToCart(product) {
        const existing = this.cart.find(item => item.id === product.id);
        if (existing) {
            existing.quantity += product.quantity;
        } else {
            this.cart.push(product);
        }
        this.save();
        this.updateCartCount();
    }

    removeFromCart(productId) {
        this.cart = this.cart.filter(item => item.id !== productId);
        this.save();
    }

    updateQuantity(productId, quantity) {
        const item = this.cart.find(item => item.id === productId);
        if (item && quantity > 0) {
            item.quantity = quantity;
            this.save();
        }
    }

    getTotal() {
        return this.cart.reduce((total, item) => total + (item.price * item.quantity), 0);
    }

    getCartCount() {
        return this.cart.reduce((count, item) => count + item.quantity, 0);
    }

    save() {
        localStorage.setItem('cart', JSON.stringify(this.cart));
    }

    saveAddress(address) {
        this.address = address;
        localStorage.setItem('address', JSON.stringify(address));
    }

    updateCartCount() {
        const el = document.querySelector('.cart-count');
        if (el) {
            const count = this.getCartCount();
            el.style.display = count > 0 ? 'flex' : 'none';
            el.textContent = count;
        }
    }

    getWhatsAppMessage(user) {
        if (this.cart.length === 0) return '';

        const text = this.cart.map(i => `${i.name} x${i.quantity} - R$ ${(i.price * i.quantity).toFixed(2)}`).join('%0A');
        const total = this.getTotal().toFixed(2);

        let msg = `*NOVO PEDIDO - Love Makeup BL*%0A%0A`;
        msg += `*Cliente:* ${user?.nome || 'Não informado'}%0A`;
        msg += `*Email:* ${user?.email || 'Não informado'}%0A`;
        msg += `*Telefone:* ${user?.telefone || 'Não informado'}%0A`;
        msg += `*Endereço de Entrega:* ${this.address?.endereco || 'Não informado'}%0A`;
        msg += `*Cidade:* ${this.address?.cidade || 'Não informado'}%0A`;
        msg += `*CEP:* ${this.address?.cep || 'Não informado'}%0A%0A`;
        msg += `*Produtos Solicitados:*%0A${text}%0A%0A`;
        msg += `*Total: R$ ${total}*%0A%0A`;
        msg += `Obrigado pela compra! 💖`;

        return msg;
    }

    checkout(user) {
        if (this.cart.length === 0) {
            alert('Seu carrinho está vazio!');
            return;
        }

        const msg = this.getWhatsAppMessage(user);
        const whatsappNumber = '5598984067365'; // Número da loja
        window.open(`https://wa.me/${whatsappNumber}?text=${msg}`, '_blank');

        this.cart = [];
        this.save();
    }

    clearCart() {
        this.cart = [];
        this.save();
    }
}

let cartManager = new CartManager();

function addToCart(id) {
    const card = document.querySelector(`[data-product-id="${id}"]`);
    const product = {
        id: id,
        name: card.querySelector('.product-name').textContent,
        price: parseFloat(card.querySelector('.product-price').textContent.replace('R$ ', '')),
        image: card.querySelector('.product-image').src,
        quantity: 1
    };
    cartManager.addToCart(product);
    alert('Produto adicionado ao carrinho!');
}

function removeFromCart(id) {
    cartManager.removeFromCart(id);
    location.reload();
}

function updateCart(id, quantity) {
    const qty = parseInt(quantity);
    if (qty > 0) {
        cartManager.updateQuantity(id, qty);
    }
}

function openTab(evt, name) {
    document.querySelectorAll('.tab-content').forEach(t => t.classList.remove('active'));
    document.querySelectorAll('.tab-button').forEach(b => b.classList.remove('active'));
    const tab = document.getElementById(name);
    if (tab) tab.classList.add('active');
    if (evt) evt.target.classList.add('active');
}

function filterProducts(category) {
    if (category === 'all') {
        document.querySelectorAll('[data-category]').forEach(p => {
            p.style.display = 'block';
        });
    } else {
        document.querySelectorAll('[data-category]').forEach(p => {
            p.style.display = p.dataset.category === category ? 'block' : 'none';
        });
    }
}

document.addEventListener('DOMContentLoaded', function() {
    cartManager.updateCartCount();
});



