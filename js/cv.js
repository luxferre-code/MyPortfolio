const btnCV = document.querySelector('#visualize-cv');
const dlCV = document.querySelector('#download-cv');

btnCV.addEventListener('click', (e) => {
    e.preventDefault();
    btnCV.style.display = 'none';

    // Create a PDF.js object
    const pdfjsLib = window['pdfjs-dist/build/pdf'];

    // Ensure the workerSrc is set
    pdfjsLib.GlobalWorkerOptions.workerSrc = 'https://cdnjs.cloudflare.com/ajax/libs/pdf.js/2.8.335/pdf.worker.min.js';

    // Load the PDF file
    pdfjsLib.getDocument('contents/cv.pdf').promise.then(function(pdf) {
        // Get the first page
        pdf.getPage(1).then(function(page) {
            const scale = 1.5;
            const viewport = page.getViewport({ scale: scale });

            // Create a canvas
            const canvas = document.getElementById('cv-canvas');
            canvas.style.display = 'flex';
            const context = canvas.getContext('2d');
            canvas.height = viewport.height;
            canvas.width = viewport.width;

            // Render the PDF page into the canvas context
            page.render({
                canvasContext: context,
                viewport: viewport
            });
            dlCV.style.display = 'flex';
        });
    }).catch(function(error) {
        console.error('Error loading PDF:', error);
    });
});

dlCV.addEventListener('click', (e) => {
    e.preventDefault();
    // Créer une nouvelle page et télécharger contents/cv.pdf
    const a = document.createElement('a');
    a.href = 'contents/cv.pdf';
    a.download = 'CV - Valentin THUILLIER.pdf';
    a.click();
});