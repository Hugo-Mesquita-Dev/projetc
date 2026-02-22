// Cart Manager
class CartManager {
    constructor() {
        this.cart = JSON.parse(localStorage.getItem('cart')) || [];
        this.user = JSON.parse(localStorage.getItem('user')) || null;
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

    getTotal() {
        return this.cart.reduce((total, item) => total + (item.price * item.quantity), 0);
    }

    getCartCount() {
        return this.cart.reduce((count, item) => count + item.quantity, 0);
    }

    save() {
        localStorage.setItem('cart', JSON.stringify(this.cart));
    }

    updateCartCount() {
        const el = document.querySelector('.cart-count');
        if (el) {
            const count = this.getCartCount();
            el.style.display = count > 0 ? 'flex' : 'none';
            el.textContent = count;
        }
    }

    checkout() {
        if (this.cart.length === 0) return;
        const text = this.cart.map(i => `${i.name} x${i.quantity}`).join('%0A');
        const total = this.getTotal().toFixed(2);
        const msg = `Gostaria de comprar:%0A${text}%0ATotal: R$ ${total}`;
        window.open(`https://wa.me/5598984067365?text=${msg}`, '_blank');
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
    alert('Produto adicionado!');
}

function openTab(evt, name) {
    document.querySelectorAll('.tab-content').forEach(t => t.classList.remove('active'));
    document.querySelectorAll('.tab-button').forEach(b => b.classList.remove('active'));
    const tab = document.getElementById(name);
    if (tab) tab.classList.add('active');
    if (evt) evt.target.classList.add('active');
}

document.addEventListener('DOMContentLoaded', function() {
    cartManager.updateCartCount();
});

